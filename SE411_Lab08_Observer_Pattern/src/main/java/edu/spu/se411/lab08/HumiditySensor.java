package edu.spu.se411.lab08;

public final class HumiditySensor extends Sensor {
    public HumiditySensor() {
        super("Humidity");
    }

    @Override
    public HumiditySensor clone() {
        return (HumiditySensor) super.clone();
    }
}
