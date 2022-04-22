package com.isep.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class History
 */
public class History {

    //
    // Fields
    //
    private StringProperty content;

    //
    // Constructors
    //
    public History(String content){
        this.content = new SimpleStringProperty(content);
    }

    //
    // Methods
    //
    public String getContent() {
        return content.get();
    }

    public StringProperty contentProperty() {
        return content;
    }

}
