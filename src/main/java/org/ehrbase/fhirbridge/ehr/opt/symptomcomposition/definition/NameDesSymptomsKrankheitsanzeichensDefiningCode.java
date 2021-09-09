package org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum NameDesSymptomsKrankheitsanzeichensDefiningCode implements EnumValueSet {
  DIARRHEA_FINDING("Diarrhea (finding)", "", "SNOMED Clinical Terms", "62315008"),

  ASTHENIA_FINDING("Asthenia (finding)", "", "SNOMED Clinical Terms", "13791008"),

  CLOUDED_CONSCIOUSNESS_FINDING("Clouded consciousness (finding)", "", "SNOMED Clinical Terms", "40917007"),

  ASYMPTOMATIC_FINDING("Asymptomatic (finding)", "", "SNOMED Clinical Terms", "84387000"),

  DRY_COUGH_FINDING("Dry cough (finding)", "", "SNOMED Clinical Terms", "11833005"),

  CHEST_PAIN_FINDING("Chest pain (finding)", "", "SNOMED Clinical Terms", "29857009"),

  MUSCLE_PAIN_FINDING("Muscle pain (finding)", "", "SNOMED Clinical Terms", "68962001"),

  SEIZURE_FINDING("Seizure (finding)", "", "SNOMED Clinical Terms", "91175000"),

  VOMITING_DISORDER("Vomiting (disorder)", "", "SNOMED Clinical Terms", "422400008"),

  PAIN_FINDING("Pain (finding)", "", "SNOMED Clinical Terms", "22253000"),

  PRODUCTIVE_COUGH_FINDING("Productive cough (finding)", "", "SNOMED Clinical Terms", "28743005"),

  NAUSEA_FINDING("Nausea (finding)", "", "SNOMED Clinical Terms", "422587007"),

  DISTURBANCE_OF_CONSCIOUSNESS_FINDING("Disturbance of consciousness (finding)", "", "SNOMED Clinical Terms", "3006004"),

  LOSS_OF_SENSE_OF_SMELL_FINDING("Loss of sense of smell (finding)", "", "SNOMED Clinical Terms", "44169009"),

  PAIN_IN_THROAT_FINDING("Pain in throat (finding)", "", "SNOMED Clinical Terms", "162397003"),

  ERUPTION_OF_SKIN_DISORDER("Eruption of skin (disorder)", "", "SNOMED Clinical Terms", "271807003"),

  HEADACHE_FINDING("Headache (finding)", "", "SNOMED Clinical Terms", "25064002"),

  JOINT_PAIN_FINDING("Joint pain (finding)", "", "SNOMED Clinical Terms", "57676002"),

  FATIGUE_FINDING("Fatigue (finding)", "", "SNOMED Clinical Terms", "84229001"),

  WHEEZING_FINDING("Wheezing (finding)", "", "SNOMED Clinical Terms", "56018004"),

  LOSS_OF_TASTE_FINDING("Loss of taste (finding)", "", "SNOMED Clinical Terms", "36955009"),

  BLEEDING_FINDING("Bleeding (finding)", "", "SNOMED Clinical Terms", "131148009"),

  INDRAWING_OF_RIBS_DURING_RESPIRATION_FINDING("Indrawing of ribs during respiration (finding)", "", "SNOMED Clinical Terms", "248567008"),

  UNABLE_TO_WALK_FINDING("Unable to walk (finding)", "", "SNOMED Clinical Terms", "282145008"),

  NASAL_DISCHARGE_FINDING("Nasal discharge (finding)", "", "SNOMED Clinical Terms", "64531003"),

  SKIN_ULCER_DISORDER("Skin ulcer (disorder)", "", "SNOMED Clinical Terms", "46742003"),

  ABDOMINAL_PAIN_FINDING("Abdominal pain (finding)", "", "SNOMED Clinical Terms", "21522001"),

  COUGH_FINDING("Cough (finding)", "", "SNOMED Clinical Terms", "49727002"),

  MALAISE_FINDING("Malaise (finding)", "", "SNOMED Clinical Terms", "367391008"),

  HEMOPTYSIS_FINDING("Hemoptysis (finding)", "", "SNOMED Clinical Terms", "66857006"),

  FEVER_GREATER_THAN1004_FAHRENHEIT38_CELSIUS_FINDING("Fever greater than 100.4 Fahrenheit / 38° Celsius (finding)", "", "SNOMED Clinical Terms", "426000000"),

  CHILL_FINDING("Chill (finding)", "", "SNOMED Clinical Terms", "43724002"),

  RIGOR_FINDING("Rigor (finding)", "", "SNOMED Clinical Terms", "38880002"),

  LYMPHADENOPATHY_DISORDER("Lymphadenopathy (disorder)", "", "SNOMED Clinical Terms", "30746006"),

  FEVER_FINDING("Fever (finding)", "", "SNOMED Clinical Terms", "386661006"),

  FEELING_FEVERISH_FINDING("Feeling feverish (finding)", "", "SNOMED Clinical Terms", "103001002"),

  NASAL_CONGESTION_FINDING("Nasal congestion (finding)", "", "SNOMED Clinical Terms", "68235000"),

  LOSS_OF_APPETITE_FINDING("Loss of appetite (finding)", "", "SNOMED Clinical Terms", "79890006"),

  CONJUNCTIVITIS_DISORDER("Conjunctivitis (disorder)", "", "SNOMED Clinical Terms", "9826008"),

  DYSPNEA_FINDING("Dyspnea (finding)", "", "SNOMED Clinical Terms", "267036007");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  NameDesSymptomsKrankheitsanzeichensDefiningCode(String value, String description,
      String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public static Map<String, NameDesSymptomsKrankheitsanzeichensDefiningCode> getCodesAsMap(){
    Map<String, NameDesSymptomsKrankheitsanzeichensDefiningCode> nameDesSymptomsKrankheitsanzeichensDefiningCodeMap = new HashMap<>();
    for (NameDesSymptomsKrankheitsanzeichensDefiningCode nameDesSymptomsKrankheitsanzeichensDefiningCode : NameDesSymptomsKrankheitsanzeichensDefiningCode.values()) {
      nameDesSymptomsKrankheitsanzeichensDefiningCodeMap.put(nameDesSymptomsKrankheitsanzeichensDefiningCode.getCode(), nameDesSymptomsKrankheitsanzeichensDefiningCode);
    }
    return nameDesSymptomsKrankheitsanzeichensDefiningCodeMap;
  }

  public String getValue() {
     return this.value ;
  }

  public String getDescription() {
     return this.description ;
  }

  public String getTerminologyId() {
     return this.terminologyId ;
  }

  public String getCode() {
     return this.code ;
  }
}
