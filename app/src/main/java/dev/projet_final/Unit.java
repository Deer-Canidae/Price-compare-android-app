package dev.projet_final;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;

public class Unit {
    //Unit id
    public static final int PER_KILO = 0;
    public static final int PER_POUND = 1;
    public static final int PER_LITER = 2;
    public static final int PER_GALLON = 3;
    public static final int PER_FLOZ = 4;
    public static final int PER_ML = 5;
    public static final int PER_G = 6;
    public static final int PER_OZ = 7;
    //Unit Strings
    private static final int PER_POUND_STRING = R.string.per_pound;
    private static final int PER_KILO_STRING = R.string.per_kilo;
    private static final int PER_GALLON_STRING = R.string.per_gallon;
    private static final int PER_LITER_STRING = R.string.per_liter;
    private static final int PER_FLOZ_STRING = R.string.per_floz;
    private static final int PER_ML_STRING = R.string.per_ml;
    private static final int PER_G_STRING = R.string.per_g;
    private static final int PER_OZ_STRING = R.string.per_oz;

    //Static Properties
    private static Unit[] units;
    private static Resources resourcesResolver;
    //Properties
    private final int unitId, unitStringRes, mapsToId;
    private final float conversionFactor;

    private Unit(int unitId, int unitStringRes, int mapsToId, float conversionFactor) {
        this.unitId = unitId;
        this.unitStringRes = unitStringRes;
        this.mapsToId = mapsToId;
        this.conversionFactor = conversionFactor;
    }

    @NonNull
    public static Unit[] getUnitList(@NonNull Context context) {
        if (units == null) {
            resourcesResolver = context.getResources();
            units = new Unit[]{
                    new Unit(PER_KILO, PER_KILO_STRING, PER_KILO, 1f),
                    new Unit(PER_POUND, PER_POUND_STRING, PER_KILO, 0.4536f),
                    new Unit(PER_LITER, PER_LITER_STRING, PER_LITER, 1f),
                    new Unit(PER_GALLON, PER_GALLON_STRING, PER_LITER, 3.785f),
                    new Unit(PER_FLOZ, PER_FLOZ_STRING, PER_LITER, 0.02957f),
                    new Unit(PER_ML, PER_ML_STRING, PER_LITER, 0.001f),
                    new Unit(PER_G, PER_G_STRING, PER_KILO, 0.001f),
                    new Unit(PER_OZ, PER_OZ_STRING, PER_KILO, 0.02835f)
            };
        }
        return units;
    }

    @NonNull
    @Override
    public String toString() {
        return resourcesResolver.getString(unitStringRes);
    }

    public int getUnitId() {
        return unitId;
    }

    public int getUnitStringRes() {
        return unitStringRes;
    }

    public static Unit getMatchingSiUnit(@NonNull Unit unit, @NonNull Context ctx) {
        Unit[] units = getUnitList(ctx);
        return units[unit.mapsToId];
    }

    public float getConversionFactor() {
        return this.conversionFactor;
    }
}
