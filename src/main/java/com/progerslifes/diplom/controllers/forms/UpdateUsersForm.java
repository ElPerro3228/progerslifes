package com.progerslifes.diplom.controllers.forms;

import com.progerslifes.diplom.entity.User;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

public class UpdateUsersForm {
    @Valid
    private List<User> users;
    @Min(0)
    private int page;
    private int pages;

    public List<User> getUsers() {
        return users;
    }

    public UpdateUsersForm(List<User> users, int page, int pages) {
        this.users = users;
        this.page = page;
        this.pages = pages;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
