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
    //Unit Strings
    private static final int PER_POUND_STRING = R.string.per_pound;
    private static final int PER_KILO_STRING = R.string.per_kilo;
    private static final int PER_GALLON_STRING = R.string.per_gallon;
    private static final int PER_LITER_STRING = R.string.per_liter;
    //Static Properties
    private static Unit[] units;
    private static Resources resourcesResolver;
    //Properties
    private final int unitId, unitStringRes;

    private Unit(int unitId, int unitStringRes) {
        this.unitId = unitId;
        this.unitStringRes = unitStringRes;
    }

    @NonNull
    public static Unit[] getUnitList(@NonNull Context context) {
        if (units == null) {
            resourcesResolver = context.getResources();
            units = new Unit[]{
                    new Unit(PER_KILO, PER_KILO_STRING),
                    new Unit(PER_POUND, PER_POUND_STRING),
                    new Unit(PER_LITER, PER_LITER_STRING),
                    new Unit(PER_GALLON, PER_GALLON_STRING)
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

    public static int getStringRes(int unitId, Context ctx) {
        Unit[] units = getUnitList(ctx);
        for (Unit unit : units) {
            if (unit.getUnitId() == unitId)
                return unit.getUnitStringRes();
        }
        return 0;
    }
}
