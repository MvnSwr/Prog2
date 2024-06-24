package Java.Prog_2.U04.A04;

import Java.Prog_2.U04.A03.Ringpuffer;

public class Temperatursensor {
    private Ringpuffer<Float> temperaturWerte;

    public Temperatursensor() {
        temperaturWerte = new Ringpuffer<>(24);
        for (int i = 0; i < 24; i++) {
            temperaturWerte.addLast(Float.NaN);
        }
    }

    public void neueMessung(Float wert) {
        temperaturWerte.addLast(wert);
    }

    public Float aktuelleTemperatur() {
        Float tmp = temperaturWerte.removeLast();
        temperaturWerte.addLast(tmp);
        return tmp;
    }

    public Float durchschnittstemperatur() {
        Float med = 0.0F;
        Float count = 0.0F;
        for (int i = 0; i < 24; i++) {
            if (temperaturWerte.get(i) != Float.NaN) {
                med += temperaturWerte.get(i);
                count += 1.0F;
            }
        }
        return med / count;
    }

    public void reset() {
        for (int i = 0; i < 24; i++) {
            temperaturWerte.addLast(Float.NaN);
        }
    }
}