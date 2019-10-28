package Gui2048;/*  File Header:
 *  Name: Yunjun Xia
 *  Login: cs8bwaqa
 *  Email: yux079@ucsd.edu
 *  Date: 2/25/2018
 *  File: Constants2048.java
 *  Sources of Help: None
 *  Summary of the file: Constants used in Gui2048 Class.
 */

import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;
import java.io.*;

/* Class Header:
 * Name: Constants2048
 * Purpose: Create constant tile width, font size and colors used in Gui2048
 * Capacity: 4 static final int
 *           16 static final color
 */
public class Constants2048 {
    public static final int TILE_WIDTH = 106;

    public static final int TEXT_SIZE_LOW = 55; // Low value tiles (2,4,8,etc)
    public static final int TEXT_SIZE_MID = 45; // Mid value tiles 
    //(128, 256, 512)
    public static final int TEXT_SIZE_HIGH = 35; // High value tiles 
    //(1024, 2048, Higher)

    // Fill colors for each of the Tile values
    public static final Color COLOR_EMPTY = Color.rgb(238, 228, 218, 0.35);
    public static final Color COLOR_2 = Color.rgb(238, 228, 218);
    public static final Color COLOR_4 = Color.rgb(237, 224, 200);
    public static final Color COLOR_8 = Color.rgb(242, 177, 121);
    public static final Color COLOR_16 = Color.rgb(245, 149, 99);
    public static final Color COLOR_32 = Color.rgb(246, 124, 95);
    public static final Color COLOR_64 = Color.rgb(246, 94, 59);
    public static final Color COLOR_128 = Color.rgb(237, 207, 114);
    public static final Color COLOR_256 = Color.rgb(237, 204, 97);
    public static final Color COLOR_512 = Color.rgb(237, 200, 80);
    public static final Color COLOR_1024 = Color.rgb(237, 197, 63);
    public static final Color COLOR_2048 = Color.rgb(237, 194, 46);
    public static final Color COLOR_OTHER = Color.BLACK;
    public static final Color COLOR_GAME_OVER = Color.rgb(238, 228, 218, 0.73);

    public static final Color COLOR_VALUE_LIGHT = Color.rgb(249, 246, 242); 
    // For tiles >= 8

    public static final Color COLOR_VALUE_DARK = Color.rgb(119, 110, 101); 
    // For tiles < 8
}
