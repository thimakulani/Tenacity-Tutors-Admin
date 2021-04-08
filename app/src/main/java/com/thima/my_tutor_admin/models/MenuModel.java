package com.thima.my_tutor_admin.models;

public class MenuModel {
    private String Title;
    private int Icon;
    private int Color;

    public int getColor() {
        return Color;
    }

    public void setColor(int color) {
        Color = color;
    }

    public MenuModel() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int icon) {
        Icon = icon;
    }

    public MenuModel(String title, int icon, int color) {
        Title = title;
        Icon = icon;
        Color = color;
    }
}
