package org.ehrbase.fhirbridge.ehr.converter.specific.stationaererversorgungsfall;

import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.ArtDerEntlassungDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.AufnahmeanlassDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.AufnahmegrundDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.FallstatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.KlinischerZustandDesPatientenDefiningCode;

import java.util.HashMap;
import java.util.Map;


class StationaererVersorgungsfallDefiningCodeMaps {

    private static final Map<String, AufnahmegrundDefiningCode> aufnahmeGrundMap = new HashMap<>();
    private static final Map<String, AufnahmeanlassDefiningCode> aufnahmeAnlassMap = new HashMap<>();
    private static final Map<String, ArtDerEntlassungDefiningCode> artDerEntlassungMap = new HashMap<>();
    private static final Map<String, KlinischerZustandDesPatientenDefiningCode> klinischerZustandMap = new HashMap<>();
    private static final Map<String, FallstatusDefiningCode> fallStatusMap = new HashMap<>();

    private StationaererVersorgungsfallDefiningCodeMaps() {
        throw new IllegalStateException("Utility class");
    }

    static {

        for(AufnahmegrundDefiningCode aufnahmegrundDefiningCode : AufnahmegrundDefiningCode.values()) {

            aufnahmeGrundMap.put(aufnahmegrundDefiningCode.getCode(), aufnahmegrundDefiningCode);
        }

        for(AufnahmeanlassDefiningCode aufnahmeanlassDefiningCode : AufnahmeanlassDefiningCode.values()) {
            aufnahmeAnlassMap.put(aufnahmeanlassDefiningCode.getCode(), aufnahmeanlassDefiningCode);
        }

        for(ArtDerEntlassungDefiningCode artDerEntlassungDefiningCode : ArtDerEntlassungDefiningCode.values()) {
            artDerEntlassungMap.put(artDerEntlassungDefiningCode.getCode(), artDerEntlassungDefiningCode);
        }

        for(KlinischerZustandDesPatientenDefiningCode klinischerZustandDesPatientenDefiningCode : KlinischerZustandDesPatientenDefiningCode.values()) {
            klinischerZustandMap.put(klinischerZustandDesPatientenDefiningCode.getCode(), klinischerZustandDesPatientenDefiningCode);
        }

        for(FallstatusDefiningCode fallstatusDefiningCode : FallstatusDefiningCode.values()) {
            fallStatusMap.put(fallstatusDefiningCode.getCode(), fallstatusDefiningCode);
        }
    }

    static Map<String, AufnahmegrundDefiningCode> getAufnahmeGrundMap() {

        return aufnahmeGrundMap;
    }

    static Map<String, AufnahmeanlassDefiningCode> getAufnahmeAnlassMap() {

        return aufnahmeAnlassMap;
    }

    static Map<String, ArtDerEntlassungDefiningCode> getArtDerEntlassungMap() {

        return artDerEntlassungMap;
    }

    static Map<String, KlinischerZustandDesPatientenDefiningCode> getKlinischerZustandMap() {

        return klinischerZustandMap;
    }

    static Map<String, FallstatusDefiningCode> getFallStatusMap() {

        return fallStatusMap;
    }
}
