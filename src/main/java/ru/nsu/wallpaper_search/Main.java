package ru.nsu.wallpaper_search;

import ru.nsu.wallpaper_search.gui.GuiController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GuiController guiController = new GuiController(()-> System.out.println("notify"));
    }

}