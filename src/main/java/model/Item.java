/*- 
 * Classname:             Item.java 
 * 
 * Version information:   (versão) 
 * 
 * Date:                  10/02/2015 - 20:00:33 
 * 
 * author:                Jonas Mayer (jonas.mayer.developer@gmail.com) 
 * Copyright notice:      (informações do método, pra que serve, idéia principal) 
 */
package model;

import java.time.LocalDate;

public class Item {

    private boolean _active;
    private String _text;
    private LocalDate _date;

    public Item() {
        _active = false;
        _date = LocalDate.now();
    }

    public void setText(String text) {
        _text = text;
    }

    public void setActive(boolean active) {
        _active = active;
    }

    public void setDate(LocalDate date) {
        _date = date;
    }

    public String getText() {
        return _text;
    }

    public boolean isActive() {
        return _active;
    }

    public LocalDate getDate() {
        return _date;
    }

}//fim da classe Item 
