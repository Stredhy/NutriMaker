/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javafx.nutrimaker.animations;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 *
 * @author mimoe
 */
public class AnimationPersonalized {
    public static void setFadeAndScaleAnimation(Node n){
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3),n);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3),n);
        ScaleTransition expand = new ScaleTransition(Duration.seconds(0.3),n);
        ScaleTransition reduce = new ScaleTransition(Duration.seconds(0.3),n);
        
        fadeIn.setFromValue(1.0);
        fadeIn.setToValue(0.5);
        fadeIn.setAutoReverse(false);
        
        expand.setToX(1.1);
        expand.setToY(1.1);
        
        n.setOnMouseEntered(e -> {
            expand.play();
            fadeIn.play();
        });
        
        fadeOut.setFromValue(0.5);
        fadeOut.setToValue(1.0);
        fadeOut.setCycleCount(1);
        fadeOut.setAutoReverse(false);
        
        reduce.setToX(1);
        reduce.setToY(1);
        
        n.setOnMouseExited(e -> {
            reduce.play();
            fadeOut.play();
        });
    }
    
    public static void setFadeAnimation(Node n){
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3),n);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3),n);
        fadeIn.setFromValue(1.0);
        fadeIn.setToValue(0.5);
        fadeIn.setAutoReverse(false);
        
        n.setOnMouseEntered(e -> {
            fadeIn.play();
        });
        
        fadeOut.setFromValue(0.5);
        fadeOut.setToValue(1.0);
        fadeOut.setCycleCount(1);
        fadeOut.setAutoReverse(false);
        
        n.setOnMouseExited(e -> {
            fadeOut.play();
        });
    }
    
    public static void setScaleAnimation(Node n){
        ScaleTransition expand = new ScaleTransition(Duration.seconds(0.3),n);
        ScaleTransition reduce = new ScaleTransition(Duration.seconds(0.3),n);
        
        expand.setToX(1.1);
        expand.setToY(1.1);
        
        n.setOnMouseEntered(e -> {
            expand.play();
        });
        
        reduce.setToX(1);
        reduce.setToY(1);
        
        n.setOnMouseExited(e -> {
            reduce.play();
        });
    }
}
