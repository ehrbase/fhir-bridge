package org.ehrbase.fhirbridge.ehr.converter.pulseoximetry;

import java.util.ArrayList;

public enum PulseOxymetryCode {
    CODING_1(new ArrayList<>() {{
        new ArrayList<String>() {{
            add("http://loinc.org");
            add("2708-6");
        }};
    }}),

    CODING_2(new ArrayList<>() {{
        new ArrayList<String>() {{
            add("http://snomed.info/sct");
            add("431314004");
        }};
    }}),

    CODING_3(new ArrayList<>() {{
        new ArrayList<String>() {{
            add("http://loinc.org");
            add("2708-6");
        }};
        new ArrayList<String>() {{
            add("http://snomed.info/sct");
            add("431314004");
        }};
    }}),

    CODING_4(new ArrayList<>() {{
        new ArrayList<String>() {{
            add("http://loinc.org");
            add("2708-6");
        }};
        new ArrayList<String>() {{
            add("http://snomed.info/sct");
            add("59408-5");
        }};
    }});

    private final ArrayList<ArrayList<String>> code;

    PulseOxymetryCode(ArrayList<ArrayList<String>> code) {
        this.code = code;
    }

    public ArrayList<ArrayList<String>> getCode() {
        return code;
    }
}
