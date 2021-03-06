package org.selfassess.utils;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 22.12.12
 * Time: 12:52
 * To change this template use File | Settings | File Templates.
 */
public class EV1ValueFactory {

    private Double[][] parameterWeights = new Double[][]{
            {
                    0.0581, 0.0291, 0.0502, 0.0461, 0.0522,
                    0.061, 0.0522, 0.0661, 0.0661, 0.1001,
                    0.0513, 0.0371, 0.0302, 0.0302, 0.0433,
                    0.0391, 0.0383, 0.0449, 0.0582, 0.0462
            }, //M1Model
            {
                    0.0504, 0.0616, 0.0591, 0.0563, 0.0646,
                    0.0604, 0.045, 0.0604, 0.0596, 0.0474,
                    0.07, 0.0626, 0.0596, 0.0533, 0.0646,
                    0.0675, 0.0576
            }, //M2Model
            {
                    0.0356, 0.036, 0.0345, 0.0334, 0.0366,
                    0.0345, 0.036, 0.0334, 0.034, 0.0319,
                    0.0319, 0.0286, 0.0308, 0.0331, 0.0255,
                    0.0266, 0.0286, 0.0292, 0.0297, 0.0263,
                    0.0328, 0.0344, 0.0312, 0.0274, 0.0294,
                    0.0283, 0.0254, 0.0239, 0.0319, 0.0326,
                    0.0316, 0.0349
            },//M3
            {
                    0.0744, 0.0721, 0.0653, 0.0559, 0.0688,
                    0.0583, 0.0619, 0.0706, 0.0501, 0.0605,
                    0.0616, 0.0619, 0.0651, 0.0557, 0.0513,
                    0.0665
            }, //M4
            {
                    0.0586, 0.0512, 0.0398, 0.0355, 0.0398,
                    0.0583, 0.0518, 0.0292, 0.0479, 0.044,
                    0.0581, 0.0415, 0.0331, 0.045, 0.0491,
                    0.0331, 0.0368, 0.0368, 0.039, 0.0433,
                    0.0436, 0.043, 0.0415
            }, //M5
            {
                    0.0857, 0.0628, 0.0628, 0.0628, 0.0842,
                    0.0857, 0.0845, 0.0651, 0.0651, 0.0776,
                    0.0651, 0.0671, 0.0607, 0.0708
            }, //M6
            {
                    0.0405, 0.0365, 0.0389, 0.0319, 0.0451,
                    0.0448, 0.0458, 0.0442, 0.0365, 0.0436,
                    0.0384, 0.0389, 0.0412, 0.0412, 0.0436,
                    0.0436, 0.0392, 0.0436, 0.0408, 0.0364,
                    0.0337, 0.0364, 0.0368, 0.0392, 0.0392
            }, //M7
            {
                    0.0852, 0.0779, 0.097, 0.0814, 0.0777,
                    0.074, 0.0639, 0.0758, 0.0646, 0.0646,
                    0.0676, 0.0889, 0.0814


            }, //M8
            {
                    1.0, 1.0, 1.0, 1.0, 1.0,
                    1.0, 1.0, 1.0, 1.0, 1.0,
                    1.0, 1.0, 1.0, 1.0, 1.0,
                    1.0, 1.0, 1.0, 1.0, 1.0,
                    1.0, 1.0, 1.0
            }, //M9. No value for M9 is set in truth; EV_m9 is defined by minimum
            {
                    0.2, 0.2, 0.2, 0.2, 0.2

            },//M10
    };


    private Integer[][] requirement = new Integer[][]{
            {1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1}, //EV1Model

            {0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //M2Model

            {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1,
                    1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, //M3
            {1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1}, //M4
            {1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1,
                    0, 1, 1, 1, 1, 0, 1, 1}, //M5
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1}, //M6
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1}, //M7
            {0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1}, //M8
            {1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1}, //M9
            {1, 1, 1, 1, 1}, //M10


    };

    private Integer[] counts = new Integer[]{20, 17, 32, 16, 23, 14, 25, 13, 23, 5};


    public EV1ValueFactory() {
    }


    public Double[][] getParameterWeights() {
        return parameterWeights;
    }

    public Integer[][] getRequirement() {
        return requirement;
    }


    public Integer[] getCounts() {
        return counts;
    }


}
