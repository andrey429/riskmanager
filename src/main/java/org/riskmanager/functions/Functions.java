package org.riskmanager.functions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 22.12.12
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
public class Functions {

    public static String toDoubleArrayString(Object[][] a) {

        StringBuilder representation = new StringBuilder();

        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";



        representation.append('[');
        for (int i = 0; ; i++) {

            representation.append(Arrays.toString(a[i]));
            if (i == iMax)
                return representation.append(']').toString();
            representation.append(", ");
        }
    }

    public static String toSingleArrayString(Object[] array) {
        return Arrays.toString(array);
    }
}
