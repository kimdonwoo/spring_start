package com.example.study.model;

//Controller의 getMultiParamter에서 @RequestParam으로
//account, email, page 3개를 받기위해
//code의 generator를 사용하여 getter/setter부르면 편리함

public class SearchParam {

    private String account;
    private String email;
    private int page;

    // { "account" : "", "email" : "", "page" : 0}

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
