package com.shawn.adapter;

public class AdapterTest {


    public static void main(String[] args) {
        ThreeElectricOut out = new Wash();
        out.connect();
        TV tv = new TV();
        // out 可以连接两相的电视机
        out = new ThreeElectricOutAdapter(tv);
        out.connect();
    }
}


class TV implements TwoElectricOut, Turn {
    private String name;
    public TV() {
        name = "小米电视机";
    }
    @Override
    public void connect() {
        turnOn();
    }

    @Override
    public void turnOn() {
        System.out.println(name + " turn on");
    }
}

class Wash implements ThreeElectricOut, Turn {
    private String name;
    public Wash() {
        name = "黄河排洗衣机";
    }
    @Override
    public void connect() {
        turnOn();
    }

    @Override
    public void turnOn() {
        System.out.println(name + " turn on");
    }
}
