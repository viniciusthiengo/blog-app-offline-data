package br.com.thiengo.blogapp.presenter;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import br.com.thiengo.blogapp.R;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Post extends RealmObject implements Parcelable {
    public static final String KEY = "post_key";
    public static final String POSTS_KEY = "posts";
    public static final String ID_KEY = "id";
    public static final String EH_FAVORITO_KEY = "eh-favorito";
    public static final int POST_CODE = 668;

    @PrimaryKey
    private long id;
    private String titulo;
    private String uriImagem;
    private String uriImagemBanner;
    private String sumario;
    private String conteudo;
    private boolean ehFavorito;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUriImagem() {
        return uriImagem;
    }

    public void setUriImagem(String uriImagem) {
        this.uriImagem = uriImagem;
    }

    public String getUriImagemBanner() {
        return uriImagemBanner;
    }

    public void setUriImagemBanner(String uriImagemBanner) {
        this.uriImagemBanner = uriImagemBanner;
    }

    public String getSumario() {
        return sumario;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public boolean isEhFavorito() {
        return ehFavorito;
    }

    public void setEhFavorito(boolean ehFavorito) {
        this.ehFavorito = ehFavorito;
    }

    public int getEhFavoritoIcone(){
        if( isEhFavorito() ){
            return R.drawable.ic_favorito;
        }
        return R.drawable.ic_nao_favorito;
    }

    public int getEhFavoritoIconeBig(){
        if( isEhFavorito() ){
            return R.drawable.ic_favorito_big_icone;
        }
        return R.drawable.ic_nao_favorito_big_icone;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.titulo);
        dest.writeString(this.uriImagem);
        dest.writeString(this.uriImagemBanner);
        dest.writeString(this.sumario);
        dest.writeString(this.conteudo);
        dest.writeByte(this.ehFavorito ? (byte) 1 : (byte) 0);
    }

    public Post() {}

    protected Post(Parcel in) {
        this.id = in.readLong();
        this.titulo = in.readString();
        this.uriImagem = in.readString();
        this.uriImagemBanner = in.readString();
        this.sumario = in.readString();
        this.conteudo = in.readString();
        this.ehFavorito = in.readByte() != 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public Post gerarCopia(){
        Post p = new Post();
        p.setId( id );
        p.setTitulo( titulo );
        p.setUriImagem( uriImagem );
        p.setUriImagemBanner( uriImagemBanner );
        p.setSumario( sumario );
        p.setConteudo( conteudo );
        p.setEhFavorito( ehFavorito );
        return p;
    }
}
