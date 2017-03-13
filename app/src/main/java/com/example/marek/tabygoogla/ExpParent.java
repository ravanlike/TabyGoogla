package com.example.marek.tabygoogla;

import java.util.ArrayList;

public class ExpParent
{
    private String name;
    private String text1;
    private String text2;
    private String text3;

    // ArrayList to store child objects
    private ArrayList<ExpChild> children;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public String getText1()
    {
        return text1;
    }

    public void setText1(String text1)
    {
        this.text1 = text1;
    }

    public String getText2()
    {
        return text2;
    }

    public void setText2(String text2)
    {
        this.text2 = text2;
    }

    public String getText3()
    {
        return text3;
    }

    public void setText3(String text3)
    {
        this.text3 = text3;
    }

    // ArrayList to store child objects
    public ArrayList<ExpChild> getChildren()
    {
        return children;
    }

    public void setChildren(ArrayList<ExpChild> children)
    {
        this.children = children;
    }
}