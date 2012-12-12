package org.riskmanager.domain.chapters;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 12.12.12
 * Time: 20:29
 * To change this template use File | Settings | File Templates.
 */
public class M1ChapterModel {

    private HashMap<Integer, Double> map = new HashMap<Integer, Double>();

    public HashMap<Integer, Double> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, Double> map) {
        this.map = map;
    }
}
