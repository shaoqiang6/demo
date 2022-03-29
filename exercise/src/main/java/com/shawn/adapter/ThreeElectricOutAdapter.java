package com.shawn.adapter;

public class ThreeElectricOutAdapter implements ThreeElectricOut{
    private TwoElectricOut electricOut;
    public ThreeElectricOutAdapter(TwoElectricOut twoElectricOut) {
        this.electricOut = twoElectricOut;
    }
    @Override
    public void connect() {
        this.electricOut.connect();
    }
}
