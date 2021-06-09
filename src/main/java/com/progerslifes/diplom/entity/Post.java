package com.progerslifes.diplom.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "creationdate")
    private Date creationDate;

    @Column(name = "text")
    private String text;

    @Column(name = "picturepath")
    private String picturePath;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH })
    private List<Like> likes;

    @Column(name = "likescount")
    private int likesCount;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="ancestor_id")
    private Post ancestor;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="ancestor_id")
    private List<Post> childList;

    @Transient
    private Boolean isLikedByCurrentUser;

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likeList) {
        this.likes = likeList;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public Boolean getLikedByCurrentUser() {
        return isLikedByCurrentUser;
    }

    public void setLikedByCurrentUser(Boolean likedByCurrentUser) {
        isLikedByCurrentUser = likedByCurrentUser;
    }

    public Post getAncestor() {
        return ancestor;
    }

    public void setAncestor(Post ancestor) {
        this.ancestor = ancestor;
    }

    public List<Post> getChildList() {
        return childList;
    }

    public void setChildList(List<Post> childList) {
        this.childList = childList;
    }

    public void addSubscription(Post post) {
        if (childList == null) {
            childList = new ArrayList<>();
        }
        childList.add(post);
    }

    public void deleteSubscription(Post post) {
        if (childList != null) {
            childList.remove(post);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (getId() != post.getId()) return false;
        return getUser().equals(post.getUser());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getUser().hashCode();
        return result;
    }
}
