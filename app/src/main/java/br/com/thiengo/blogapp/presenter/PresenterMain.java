package br.com.thiengo.blogapp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.blogapp.R;
import br.com.thiengo.blogapp.model.Model;
import br.com.thiengo.blogapp.view.PostActivity;
import br.com.thiengo.blogapp.view.ViewImpl;


public class PresenterMain implements PresenterImpl {
    private static PresenterMain instance;
    private Model model;
    private ViewImpl view;
    private ArrayList<Post> posts = new ArrayList<>();


    private PresenterMain(){
        model = new Model( this );
    }

    public static PresenterMain getInstance(){
        if( instance == null ){
            instance = new PresenterMain();
        }
        return instance;
    }

    @Override
    public void setView( ViewImpl view ){
        this.view = view;
    }

    @Override
    public Activity getContext() {
        return (Activity) view;
    }

    public void retrievePosts(Bundle savedInstanceState) {
        if( savedInstanceState != null ){
            posts = savedInstanceState.getParcelableArrayList( Post.POSTS_KEY );
            return;
        }
        model.retrievePosts();
    }

    @Override
    public void updateEhFavoritoPost(Post post) {
        post.setEhFavorito( !post.isEhFavorito() );
        model.updateEhFavoritoPost( post );
    }

    @Override
    public void showToast(String mensagem) {
        view.showToast( mensagem );
    }

    @Override
    public void showProgressBar(boolean status) {
        int visibilidade = status ? View.VISIBLE : View.GONE;
        view.showProgressBar( visibilidade );
    }

    @Override
    public void updateListaRecycler(Object object) {
        List<Post> postsCarregados = (List<Post>) object;
        posts.clear();
        posts.addAll( postsCarregados );
        view.updateListaRecycler();
        showProgressBar( !(posts.size() > 0) );
    }

    @Override
    public void updateItemRecycler(Post p) {
        if( p == null ){
            return;
        }

        for(int i = 0; i < posts.size(); i++ ){
            if( posts.get(i).getId() == p.getId() ){
                posts.get(i).setEhFavorito( p.isEhFavorito() );
                view.updateItemRecycler( i );
                break;
            }
        }
    }

    @Override
    public void enviarComentario( Post post ){
        throw new UnsupportedOperationException();
    }

    public void itemClicado( long viewId, Post post ){
        if( viewId == R.id.iv_favorito ){
            updateEhFavoritoPost(post);
        }
        else{
            Intent intent = new Intent( getContext(), PostActivity.class);
            intent.putExtra( Post.KEY, post );
            getContext().startActivityForResult( intent, Post.POST_CODE );
        }
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
