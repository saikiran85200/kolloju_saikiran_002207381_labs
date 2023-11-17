/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analytics;

/**
 *
 * @author harshalneelkamal
 */

import data.DataStore;
import java.util.Map;
import model.Comment;
import model.Post;
import model.User;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;


public class AnalysisHelper {
    //Find Average number of likes per comment.
    //TODO
    public void getAverageLikesPerComments() {
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        int likeNumber = 0;
        int commentNumber = comments.size();
        for (Comment c : comments.values()) {
            likeNumber += c.getLikes();
        }
        
        System.out.println("Average number of likes per comments: " + likeNumber / commentNumber);
            
    }
    
    public void getMostLikedComments(){
        DataStore data = DataStore.getInstance();
//        int maxLiked = 0;
//        int postid = 0;
//        for (Comment p : data.getComments().values()){
//            if (p.getLikes() > maxLiked ) {
//                maxLiked = p.getLikes();
//                postid = p.getPostId();
//            }
//        }
//        System.out.println(maxLiked);
//        System.out.println(postid);
        Comment commentWithMaxLikes = null;
        for(Comment c : data.getComments().values()){
            if(commentWithMaxLikes == null){
                commentWithMaxLikes = c;
            }
            if(c.getLikes() > commentWithMaxLikes.getLikes()){
                commentWithMaxLikes = c;
            } 
        }
        int post = commentWithMaxLikes.getPostId();
        System.out.println("Q2 - postid with most liked comments - "+data.getPosts().get(post).getPostId());
    }  
    
    public void getMostCommentedPost(){
        DataStore data = DataStore.getInstance();
        Post postWithMaxComments = null;
        for(Post c : data.getPosts().values()){
            if(postWithMaxComments == null){
                postWithMaxComments = c;
            }
            if(c.getComments().size() > postWithMaxComments.getComments().size()){
                postWithMaxComments = c;
            } 
        }
        int post = postWithMaxComments.getPostId();
        System.out.println("Q3 - post with most comments"+data.getPosts().get(post).getPostId());
//        System.out.println("sss"+post);
    }
    
    public void getPassiveUsers(){
        DataStore data = DataStore.getInstance();
        HashMap<Integer, Integer> userPost = new HashMap<>();
        for(Post p : data.getPosts().values()){
            if (userPost.containsKey(p.getUserId()) ){
                userPost.put(p.getUserId(), userPost.get(p.getUserId())+1);
            } else {
                userPost.put(p.getUserId(),1);
            }
        }
//        System.out.println(userPost);
        ArrayList<User> users = new ArrayList(data.getUsers().values());
        Collections.sort(users,new UserMapComparator(userPost));
        System.out.println("Q4 - ");
        for (int i = 0;i<5;i++){
            System.out.println("Inactive userID - "+users.get(i).getId()+", - post count: "+userPost.get(users.get(i).getId()));
        }
    }
    
    public void getPassiveUserSComments(){
        DataStore data = DataStore.getInstance();
        HashMap<Integer, Integer> userComment = new HashMap<>();
        for(Comment p : data.getComments().values()){
            if (userComment.containsKey(p.getUserId()) ){
                userComment.put(p.getUserId(), userComment.get(p.getUserId())+1);
            } else {
                userComment.put(p.getUserId(),1);
            }
        }
        System.out.println(userComment);
        ArrayList<User> users = new ArrayList(data.getUsers().values());
        Collections.sort(users,new UserMapComparator(userComment));
        System.out.println("Q5 - ");
        for (int i = 0;i<5;i++){
            System.out.println("Inactive userID - "+users.get(i).getId()+", - comment count: "+userComment.get(users.get(i).getId()));
        }
    }
    
    public void getPassiveUsersOverall(){
        DataStore data = DataStore.getInstance();
        HashMap<Integer, Integer> userOverAll = new HashMap<>();
        for(Comment p : data.getComments().values()){
            if (userOverAll.containsKey(p.getUserId()) ){
                userOverAll.put(p.getUserId(), userOverAll.get(p.getUserId())+1+p.getLikes());
            } else {
                userOverAll.put(p.getUserId(),1+p.getLikes());
            }
        }
        for(Post p : data.getPosts().values()){
            if (userOverAll.containsKey(p.getUserId()) ){
                userOverAll.put(p.getUserId(), userOverAll.get(p.getUserId())+1);
            } else {
                userOverAll.put(p.getUserId(),1);
            }
        }
//        System.out.println(userOverAll);
        ArrayList<User> users = new ArrayList(data.getUsers().values());
        Collections.sort(users,new UserMapComparator(userOverAll));
        System.out.println("Q6 - ");
        for (int i = 0;i<5;i++){
            System.out.println("Overall Inactive userID - "+users.get(i).getId()+", - total count: "+userOverAll.get(users.get(i).getId()));
        }
        System.out.println("Q7 - ");
        for (int i = users.size()-1;i>users.size()-6;i--){
            System.out.println("Overall Active userID - "+users.get(i).getId()+", - total count: "+userOverAll.get(users.get(i).getId()));
        }
    }
    
}
