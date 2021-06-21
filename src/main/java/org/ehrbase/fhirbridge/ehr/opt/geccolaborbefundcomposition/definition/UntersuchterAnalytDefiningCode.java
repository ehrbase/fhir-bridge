package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum UntersuchterAnalytDefiningCode implements EnumValueSet {
  FERRITIN_MASS_VOLUME_IN_SERUM_OR_PLASMA_BY_IMMUNOASSAY("Ferritin [Mass/volume] in Serum or Plasma by Immunoassay", "", "LOINC", "20567-4"),

  LYMPHOCYTES_VOLUME_IN_BLOOD_BY_AUTOMATED_COUNT("Lymphocytes [#/volume] in Blood by Automated count", "", "LOINC", "731-0"),

  CREATININE_MASS_VOLUME_IN_BODY_FLUID("Creatinine [Mass/volume] in Body fluid", "", "LOINC", "12190-5"),

  BILIRUBIN_TOTAL_MASS_VOLUME_IN_VENOUS_BLOOD("Bilirubin.total [Mass/volume] in Venous blood", "", "LOINC", "59828-4"),

  TROPONIN_I_CARDIAC_MASS_VOLUME_IN_BLOOD("Troponin I.cardiac [Mass/volume] in Blood", "", "LOINC", "42757-5"),

  PLATELETS_VOLUME_IN_BLOOD_BY_AUTOMATED_COUNT("Platelets [#/volume] in Blood by Automated count", "", "LOINC", "777-3"),

  LYMPHOCYTES_VOLUME_IN_BLOOD_BY_FLOW_CYTOMETRY_FC("Lymphocytes [#/volume] in Blood by Flow cytometry (FC)", "", "LOINC", "30364-4"),

  HEMOGLOBIN_MASS_VOLUME_IN_ARTERIAL_BLOOD("Hemoglobin [Mass/volume] in Arterial blood", "", "LOINC", "30313-1"),

  LACTATE_MOLES_VOLUME_IN_MIXED_VENOUS_BLOOD("Lactate [Moles/volume] in Mixed venous blood", "", "LOINC", "19240-1"),

  ASPARTATE_AMINOTRANSFERASE_ENZYMATIC_ACTIVITY_VOLUME_IN_SERUM_OR_PLASMA_BY_WITH_P5_P("Aspartate aminotransferase [Enzymatic activity/volume] in Serum or Plasma by With P-5'-P", "", "LOINC", "30239-8"),

  BILIRUBIN_INDIRECT_MASS_VOLUME_IN_SERUM_OR_PLASMA("Bilirubin.indirect [Mass/volume] in Serum or Plasma", "", "LOINC", "1971-1"),

  TROPONIN_I_CARDIAC_MASS_VOLUME_IN_SERUM_OR_PLASMA_BY_DETECTION_LIMIT001_NG_ML("Troponin I.cardiac [Mass/volume] in Serum or Plasma by Detection limit <= 0.01 ng/mL", "", "LOINC", "49563-0"),

  CREATININE_MOLES_VOLUME_IN_BODY_FLUID("Creatinine [Moles/volume] in Body fluid", "", "LOINC", "25386-4"),

  LACTATE_MASS_VOLUME_IN_BLOOD("Lactate [Mass/volume] in Blood", "", "LOINC", "59032-3"),

  HEMOGLOBIN_MASS_VOLUME_IN_CAPILLARY_BLOOD("Hemoglobin [Mass/volume] in Capillary blood", "", "LOINC", "30352-9"),

  LEUKOCYTES_VOLUME_IN_BLOOD_BY_ESTIMATE("Leukocytes [#/volume] in Blood by Estimate", "", "LOINC", "49498-9"),

  NEUTROPHILS_VOLUME_IN_BLOOD("Neutrophils [#/volume] in Blood", "", "LOINC", "26499-4"),

  FIBRINOGEN_PRESENCE_IN_PLATELET_POOR_PLASMA("Fibrinogen [Presence] in Platelet poor plasma", "", "LOINC", "16859-1"),

  LEUKOCYTES_VOLUME_IN_BLOOD("Leukocytes [#/volume] in Blood", "", "LOINC", "26464-8"),

  C_REACTIVE_PROTEIN_MASS_VOLUME_IN_SERUM_OR_PLASMA_BY_HIGH_SENSITIVITY_METHOD("C reactive protein [Mass/volume] in Serum or Plasma by High sensitivity method", "", "LOINC", "30522-7"),

  ANTITHROMBIN_MOLES_VOLUME_IN_PLATELET_POOR_PLASMA_BY_CHROMOGENIC_METHOD("Antithrombin [Moles/volume] in Platelet poor plasma by Chromogenic method", "", "LOINC", "3176-5"),

  ALBUMIN_MASS_VOLUME_IN_SERUM_OR_PLASMA_BY_BROMOCRESOL_GREEN_BCG_DYE_BINDING_METHOD("Albumin [Mass/volume] in Serum or Plasma by Bromocresol green (BCG) dye binding method", "", "LOINC", "61151-7"),

  LACTATE_DEHYDROGENASE_ENZYMATIC_ACTIVITY_VOLUME_IN_SERUM_OR_PLASMA_BY_LACTATE_TO_PYRUVATE_REACTION("Lactate dehydrogenase [Enzymatic activity/volume] in Serum or Plasma by Lactate to pyruvate reaction", "", "LOINC", "14804-9"),

  LACTATE_MOLES_VOLUME_IN_ARTERIAL_BLOOD("Lactate [Moles/volume] in Arterial blood", "", "LOINC", "2518-9"),

  APTT_IN_BLOOD_BY_COAGULATION_ASSAY("aPTT in Blood by Coagulation assay", "", "LOINC", "3173-2"),

  INR_IN_PLATELET_POOR_PLASMA_BY_COAGULATION_ASSAY("INR in Platelet poor plasma by Coagulation assay", "", "LOINC", "6301-6"),

  APTT_IN_PLATELET_POOR_PLASMA_BY_COAGULATION_ASSAY("aPTT in Platelet poor plasma by Coagulation assay", "", "LOINC", "14979-9"),

  PROCALCITONIN_MASS_VOLUME_IN_SERUM_OR_PLASMA_BY_IMMUNOASSAY("Procalcitonin [Mass/volume] in Serum or Plasma by Immunoassay", "", "LOINC", "75241-0"),

  HEMOGLOBIN_MASS_VOLUME_IN_ARTERIAL_BLOOD_BY_OXIMETRY("Hemoglobin [Mass/volume] in Arterial blood by Oximetry", "", "LOINC", "14775-1"),

  INTERLEUKIN6_MASS_VOLUME_IN_BODY_FLUID("Interleukin 6 [Mass/volume] in Body fluid", "", "LOINC", "49732-1"),

  INR_IN_BLOOD_BY_COAGULATION_ASSAY("INR in Blood by Coagulation assay", "", "LOINC", "34714-6"),

  PLATELETS_VOLUME_IN_PLASMA("Platelets [#/volume] in Plasma", "", "LOINC", "26516-5"),

  PLATELETS_VOLUME_IN_BLOOD_BY_ESTIMATE("Platelets [#/volume] in Blood by Estimate", "", "LOINC", "49497-1"),

  FIBRIN_D_DIMER_FEU_MASS_VOLUME_IN_BLOOD_BY_IMMUNOASSAY("Fibrin D-dimer FEU [Mass/volume] in Blood by Immunoassay", "", "LOINC", "71427-9"),

  FIBRINOGEN_MASS_VOLUME_IN_PLATELET_POOR_PLASMA_BY_COAGULATION_DERIVED("Fibrinogen [Mass/volume] in Platelet poor plasma by Coagulation.derived", "", "LOINC", "48664-7"),

  LACTATE_MASS_VOLUME_IN_ARTERIAL_BLOOD("Lactate [Mass/volume] in Arterial blood", "", "LOINC", "30242-2"),

  ASPARTATE_AMINOTRANSFERASE_ENZYMATIC_ACTIVITY_VOLUME_IN_SERUM_OR_PLASMA_BY_NO_ADDITION_OF_P5_P("Aspartate aminotransferase [Enzymatic activity/volume] in Serum or Plasma by No addition of P-5'-P", "", "LOINC", "88112-8"),

  C_REACTIVE_PROTEIN_MOLES_VOLUME_IN_SERUM_OR_PLASMA("C reactive protein [Moles/volume] in Serum or Plasma", "", "LOINC", "76485-2"),

  HEMOGLOBIN_MASS_VOLUME_IN_VENOUS_BLOOD("Hemoglobin [Mass/volume] in Venous blood", "", "LOINC", "30350-3"),

  LACTATE_DEHYDROGENASE_ENZYMATIC_ACTIVITY_VOLUME_IN_SERUM_OR_PLASMA("Lactate dehydrogenase [Enzymatic activity/volume] in Serum or Plasma", "", "LOINC", "2532-0"),

  HEMOGLOBIN_MOLES_VOLUME_IN_ARTERIAL_BLOOD("Hemoglobin [Moles/volume] in Arterial blood", "", "LOINC", "75928-2"),

  CREATININE_MOLES_VOLUME_IN_SERUM_OR_PLASMA("Creatinine [Moles/volume] in Serum or Plasma", "", "LOINC", "14682-9"),

  NEUTROPHILS_VOLUME_IN_BLOOD_BY_AUTOMATED_COUNT("Neutrophils [#/volume] in Blood by Automated count", "", "LOINC", "751-8"),

  BILIRUBIN_TOTAL_MASS_VOLUME_IN_SERUM_OR_PLASMA("Bilirubin.total [Mass/volume] in Serum or Plasma", "", "LOINC", "1975-2"),

  LACTATE_DEHYDROGENASE_ENZYMATIC_ACTIVITY_VOLUME_IN_BODY_FLUID_BY_PYRUVATE_TO_LACTATE_REACTION("Lactate dehydrogenase [Enzymatic activity/volume] in Body fluid by Pyruvate to lactate reaction", "", "LOINC", "60017-1"),

  C_REACTIVE_PROTEIN_MOLES_VOLUME_IN_SERUM_OR_PLASMA_BY_HIGH_SENSITIVITY_METHOD("C reactive protein [Moles/volume] in Serum or Plasma by High sensitivity method", "", "LOINC", "76486-0"),

  LACTATE_DEHYDROGENASE_ENZYMATIC_ACTIVITY_VOLUME_IN_SERUM_OR_PLASMA_BY_PYRUVATE_TO_LACTATE_REACTION("Lactate dehydrogenase [Enzymatic activity/volume] in Serum or Plasma by Pyruvate to lactate reaction", "", "LOINC", "14805-6"),

  ALBUMIN_MASS_VOLUME_IN_BLOOD_BY_BROMOCRESOL_PURPLE_BCP_DYE_BINDING_METHOD("Albumin [Mass/volume] in Blood by Bromocresol purple (BCP) dye binding method", "", "LOINC", "76631-1"),

  FERRITIN_MASS_VOLUME_IN_SERUM_OR_PLASMA("Ferritin [Mass/volume] in Serum or Plasma", "", "LOINC", "2276-4"),

  LACTATE_MOLES_VOLUME_IN_BLOOD("Lactate [Moles/volume] in Blood", "", "LOINC", "32693-4"),

  PLATELETS_VOLUME_IN_BLOOD("Platelets [#/volume] in Blood", "", "LOINC", "26515-7"),

  BILIRUBIN_TOTAL_MOLES_VOLUME_IN_SERUM_OR_PLASMA("Bilirubin.total [Moles/volume] in Serum or Plasma", "", "LOINC", "14631-6"),

  LACTATE_MASS_VOLUME_IN_SERUM_OR_PLASMA("Lactate [Mass/volume] in Serum or Plasma", "", "LOINC", "14118-4"),

  FIBRINOGEN_MASS_VOLUME_IN_PLATELET_POOR_PLASMA_BY_COAGULATION_ASSAY("Fibrinogen [Mass/volume] in Platelet poor plasma by Coagulation assay", "", "LOINC", "3255-7"),

  FIBRIN_D_DIMER_UNITS_VOLUME_IN_PLATELET_POOR_PLASMA_BY_IMMUNOASSAY("Fibrin D-dimer [Units/volume] in Platelet poor plasma by Immunoassay", "", "LOINC", "3246-6"),

  ASPARTATE_AMINOTRANSFERASE_ENZYMATIC_ACTIVITY_VOLUME_IN_SERUM_OR_PLASMA("Aspartate aminotransferase [Enzymatic activity/volume] in Serum or Plasma", "", "LOINC", "1920-8"),

  FIBRIN_D_DIMER_TITER_IN_PLATELET_POOR_PLASMA("Fibrin D-dimer [Titer] in Platelet poor plasma", "", "LOINC", "38898-3"),

  INR_IN_CAPILLARY_BLOOD_BY_COAGULATION_ASSAY("INR in Capillary blood by Coagulation assay", "", "LOINC", "46418-0"),

  ALBUMIN_MOLES_VOLUME_IN_SERUM_OR_PLASMA("Albumin [Moles/volume] in Serum or Plasma", "", "LOINC", "54347-0"),

  SHORT_FIBRIN_D_DIMER_FEU_AND_DDU_PANEL_PLATELET_POOR_PLASMA("Short Fibrin D-dimer FEU and DDU panel - Platelet poor plasma", "", "LOINC", "55398-2"),

  LACTATE_MOLES_VOLUME_IN_VENOUS_BLOOD("Lactate [Moles/volume] in Venous blood", "", "LOINC", "2519-7"),

  LEUKOCYTES_VOLUME_IN_BLOOD_BY_MANUAL_COUNT("Leukocytes [#/volume] in Blood by Manual count", "", "LOINC", "804-5"),

  ALBUMIN_MOLES_VOLUME_IN_SERUM_OR_PLASMA_BY_BROMOCRESOL_PURPLE_BCP_DYE_BINDING_METHOD("Albumin [Moles/volume] in Serum or Plasma by Bromocresol purple (BCP) dye binding method", "", "LOINC", "62234-0"),

  GAMMA_GLUTAMYL_TRANSFERASE_ASPARTATE_AMINOTRANSFERASE_ENZYMATIC_ACTIVITY_RATIO_IN_SERUM_OR_PLASMA("Gamma glutamyl transferase/Aspartate aminotransferase [Enzymatic activity ratio] in Serum or Plasma", "", "LOINC", "2325-9"),

  TROPONIN_I_CARDIAC_MASS_VOLUME_IN_SERUM_OR_PLASMA("Troponin I.cardiac [Mass/volume] in Serum or Plasma", "", "LOINC", "10839-9"),

  C_REACTIVE_PROTEIN_MASS_VOLUME_IN_BLOOD_BY_HIGH_SENSITIVITY_METHOD("C reactive protein [Mass/volume] in Blood by High sensitivity method", "", "LOINC", "71426-1"),

  CREATININE_MASS_VOLUME_IN_SERUM_OR_PLASMA("Creatinine [Mass/volume] in Serum or Plasma", "", "LOINC", "2160-0"),

  TROPONIN_T_CARDIAC_MASS_VOLUME_IN_VENOUS_BLOOD("Troponin T.cardiac [Mass/volume] in Venous blood", "", "LOINC", "6597-9"),

  LACTATE_MASS_VOLUME_IN_CEREBRAL_SPINAL_FLUID("Lactate [Mass/volume] in Cerebral spinal fluid", "", "LOINC", "27941-4"),

  FIBRIN_D_DIMER_UNITS_VOLUME_IN_PLATELET_POOR_PLASMA("Fibrin D-dimer [Units/volume] in Platelet poor plasma", "", "LOINC", "7799-0"),

  HEMOGLOBIN_MASS_VOLUME_IN_BLOOD("Hemoglobin [Mass/volume] in Blood", "", "LOINC", "718-7"),

  FIBRIN_D_DIMER_DDU_MASS_VOLUME_IN_PLATELET_POOR_PLASMA_BY_IMMUNOASSAY("Fibrin D-dimer DDU [Mass/volume] in Platelet poor plasma by Immunoassay", "", "LOINC", "48058-2"),

  HEMOGLOBIN_MOLES_VOLUME_IN_BLOOD("Hemoglobin [Moles/volume] in Blood", "", "LOINC", "59260-0"),

  CREATININE_MASS_VOLUME_IN_BLOOD("Creatinine [Mass/volume] in Blood", "", "LOINC", "38483-4"),

  PLATELETS_VOLUME_IN_PLATELET_RICH_PLASMA_BY_AUTOMATED_COUNT("Platelets [#/volume] in Platelet rich plasma by Automated count", "", "LOINC", "74775-8"),

  FIBRINOGEN_MASS_VOLUME_IN_PLATELET_POOR_PLASMA_BY_HEAT_DENATURATION("Fibrinogen [Mass/volume] in Platelet poor plasma by Heat denaturation", "", "LOINC", "30902-1"),

  FIBRIN_D_DIMER_DDU_MASS_VOLUME_IN_PLATELET_POOR_PLASMA("Fibrin D-dimer DDU [Mass/volume] in Platelet poor plasma", "", "LOINC", "48066-5"),

  INTERLEUKIN6_PRESENCE_IN_SERUM_OR_PLASMA("Interleukin 6 [Presence] in Serum or Plasma", "", "LOINC", "44322-6"),

  FIBRIN_D_DIMER_DDU_MASS_VOLUME_IN_BLOOD_BY_IMMUNOASSAY("Fibrin D-dimer DDU [Mass/volume] in Blood by Immunoassay", "", "LOINC", "91556-1"),

  NEUTROPHILS_VOLUME_IN_BLOOD_BY_MANUAL_COUNT("Neutrophils [#/volume] in Blood by Manual count", "", "LOINC", "753-4"),

  TROPONIN_T_CARDIAC_MASS_VOLUME_IN_SERUM_OR_PLASMA("Troponin T.cardiac [Mass/volume] in Serum or Plasma", "", "LOINC", "6598-7"),

  HEMOGLOBIN_MASS_VOLUME_IN_MIXED_VENOUS_BLOOD_BY_OXIMETRY("Hemoglobin [Mass/volume] in Mixed venous blood by Oximetry", "", "LOINC", "76768-1"),

  FERRITIN_GOAL_MASS_VOLUME_SERUM_OR_PLASMA("Ferritin goal [Mass/volume] Serum or Plasma", "", "LOINC", "86914-9"),

  HEMOGLOBIN_MASS_VOLUME_IN_MIXED_VENOUS_BLOOD("Hemoglobin [Mass/volume] in Mixed venous blood", "", "LOINC", "30351-1"),

  ALBUMIN_MASS_VOLUME_IN_SERUM_OR_PLASMA("Albumin [Mass/volume] in Serum or Plasma", "", "LOINC", "1751-7"),

  LACTATE_MOLES_VOLUME_IN_SERUM_OR_PLASMA("Lactate [Moles/volume] in Serum or Plasma", "", "LOINC", "2524-7"),

  TROPONIN_T_CARDIAC_MASS_VOLUME_IN_BLOOD("Troponin T.cardiac [Mass/volume] in Blood", "", "LOINC", "48425-3"),

  ALBUMIN_MASS_VOLUME_IN_SERUM_OR_PLASMA_BY_BROMOCRESOL_PURPLE_BCP_DYE_BINDING_METHOD("Albumin [Mass/volume] in Serum or Plasma by Bromocresol purple (BCP) dye binding method", "", "LOINC", "61152-5"),

  GAMMA_GLUTAMYL_TRANSFERASE_ENZYMATIC_ACTIVITY_VOLUME_IN_SERUM_OR_PLASMA("Gamma glutamyl transferase [Enzymatic activity/volume] in Serum or Plasma", "", "LOINC", "2324-2"),

  LACTATE_DEHYDROGENASE_ENZYMATIC_ACTIVITY_VOLUME_IN_BODY_FLUID_BY_LACTATE_TO_PYRUVATE_REACTION("Lactate dehydrogenase [Enzymatic activity/volume] in Body fluid by Lactate to pyruvate reaction", "", "LOINC", "14803-1"),

  INTERLEUKIN6_MASS_VOLUME_IN_CEREBRAL_SPINAL_FLUID("Interleukin 6 [Mass/volume] in Cerebral spinal fluid", "", "LOINC", "49919-4"),

  FERRITIN_MOLES_VOLUME_IN_SERUM_OR_PLASMA("Ferritin [Moles/volume] in Serum or Plasma", "", "LOINC", "14723-1"),

  HEMOGLOBIN_MASS_VOLUME_IN_VENOUS_BLOOD_BY_OXIMETRY("Hemoglobin [Mass/volume] in Venous blood by Oximetry", "", "LOINC", "76769-9"),

  NATRIURETIC_PEPTIDE_B_PROHORMONE_N_TERMINAL_MASS_VOLUME_IN_SERUM_OR_PLASMA("Natriuretic peptide.B prohormone N-Terminal [Mass/volume] in Serum or Plasma", "", "LOINC", "33762-6"),

  PLATELETS_VOLUME_IN_PLASMA_BY_AUTOMATED_COUNT("Platelets [#/volume] in Plasma by Automated count", "", "LOINC", "13056-7"),

  HEMOGLOBIN_MASS_VOLUME_IN_BLOOD_BY_CALCULATION("Hemoglobin [Mass/volume] in Blood by calculation", "", "LOINC", "20509-6"),

  TROPONIN_I_CARDIAC_MASS_VOLUME_IN_SERUM_OR_PLASMA_BY_HIGH_SENSITIVITY_METHOD("Troponin I.cardiac [Mass/volume] in Serum or Plasma by High sensitivity method", "", "LOINC", "89579-7"),

  PROCALCITONIN_MASS_VOLUME_IN_SERUM_OR_PLASMA("Procalcitonin [Mass/volume] in Serum or Plasma", "", "LOINC", "33959-8"),

  CREATININE_MASS_VOLUME_IN_ARTERIAL_BLOOD("Creatinine [Mass/volume] in Arterial blood", "", "LOINC", "21232-4"),

  CREATININE_MOLES_VOLUME_IN_BLOOD("Creatinine [Moles/volume] in Blood", "", "LOINC", "59826-8"),

  LYMPHOCYTES_VOLUME_IN_BLOOD_BY_MANUAL_COUNT("Lymphocytes [#/volume] in Blood by Manual count", "", "LOINC", "732-8"),

  LACTATE_MOLES_VOLUME_IN_CAPILLARY_BLOOD("Lactate [Moles/volume] in Capillary blood", "", "LOINC", "19239-3"),

  PLATELETS_VOLUME_IN_BLOOD_BY_MANUAL_COUNT("Platelets [#/volume] in Blood by Manual count", "", "LOINC", "778-1"),

  ANTITHROMBIN_UNITS_VOLUME_IN_PLATELET_POOR_PLASMA_BY_CHROMOGENIC_METHOD("Antithrombin [Units/volume] in Platelet poor plasma by Chromogenic method", "", "LOINC", "3174-0"),

  ALBUMIN_MASS_VOLUME_IN_SERUM_OR_PLASMA_BY_ELECTROPHORESIS("Albumin [Mass/volume] in Serum or Plasma by Electrophoresis", "", "LOINC", "2862-1"),

  APTT_IN_PLATELET_POOR_PLASMA_BY_COAGULATION11_SALINE("aPTT in Platelet poor plasma by Coagulation 1:1 saline", "", "LOINC", "43734-3"),

  C_REACTIVE_PROTEIN_MASS_VOLUME_IN_SERUM_OR_PLASMA("C reactive protein [Mass/volume] in Serum or Plasma", "", "LOINC", "1988-5"),

  LYMPHOCYTES_VOLUME_IN_BLOOD("Lymphocytes [#/volume] in Blood", "", "LOINC", "26474-7"),

  LACTATE_MOLES_VOLUME_IN_CEREBRAL_SPINAL_FLUID("Lactate [Moles/volume] in Cerebral spinal fluid", "", "LOINC", "2520-5"),

  PLATELETS_VOLUME_IN_CAPILLARY_BLOOD_BY_MANUAL_COUNT("Platelets [#/volume] in Capillary blood by Manual count", "", "LOINC", "74464-9"),

  TROPONIN_T_CARDIAC_MASS_VOLUME_IN_SERUM_OR_PLASMA_BY_HIGH_SENSITIVITY_METHOD("Troponin T.cardiac [Mass/volume] in Serum or Plasma by High sensitivity method", "", "LOINC", "67151-1"),

  INTERLEUKIN6_MASS_VOLUME_IN_SERUM_OR_PLASMA("Interleukin 6 [Mass/volume] in Serum or Plasma", "", "LOINC", "26881-3"),

  ANTITHROMBIN_IN_PLATELET_POOR_PLASMA_BY_CHROMO_NO_ADDITION_OF_HEPARIN("Antithrombin in Platelet poor plasma by Chromo.no addition of heparin", "", "LOINC", "91120-6"),

  LEUKOCYTES_VOLUME_IN_BLOOD_BY_AUTOMATED_COUNT("Leukocytes [#/volume] in Blood by Automated count", "", "LOINC", "6690-2"),

  C_REACTIVE_PROTEIN_MASS_VOLUME_IN_CAPILLARY_BLOOD("C reactive protein [Mass/volume] in Capillary blood", "", "LOINC", "48421-2"),

  FERRITIN_MASS_VOLUME_IN_BLOOD("Ferritin [Mass/volume] in Blood", "", "LOINC", "24373-3"),

  BILIRUBIN_TOTAL_MASS_VOLUME_IN_BLOOD("Bilirubin.total [Mass/volume] in Blood", "", "LOINC", "42719-5"),

  HEMOGLOBIN_MASS_VOLUME_IN_BLOOD_BY_OXIMETRY("Hemoglobin [Mass/volume] in Blood by Oximetry", "", "LOINC", "55782-7"),

  FIBRIN_D_DIMER_FEU_MASS_VOLUME_IN_PLATELET_POOR_PLASMA_BY_IMMUNOASSAY("Fibrin D-dimer FEU [Mass/volume] in Platelet poor plasma by Immunoassay", "", "LOINC", "48067-3"),

  ALBUMIN_MOLES_VOLUME_IN_SERUM_OR_PLASMA_BY_BROMOCRESOL_GREEN_BCG_DYE_BINDING_METHOD("Albumin [Moles/volume] in Serum or Plasma by Bromocresol green (BCG) dye binding method", "", "LOINC", "62235-7"),

  BILIRUBIN_TOTAL_MASS_VOLUME_IN_ARTERIAL_BLOOD("Bilirubin.total [Mass/volume] in Arterial blood", "", "LOINC", "59827-6"),

  APTT_IN_BLOOD_BY_COAGULATION11_SALINE("aPTT in Blood by Coagulation 1:1 saline", "", "LOINC", "16631-4");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  UntersuchterAnalytDefiningCode(String value, String description, String terminologyId,
      String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public static Map<String, UntersuchterAnalytDefiningCode> getCodesAsMap(){
    Map<String, UntersuchterAnalytDefiningCode> untersuchterAnalytDefiningCodeHashMap = new HashMap<>();
    for (UntersuchterAnalytDefiningCode untersuchterAnalytDefiningCode : UntersuchterAnalytDefiningCode.values()) {
      untersuchterAnalytDefiningCodeHashMap.put(untersuchterAnalytDefiningCode.getCode(), untersuchterAnalytDefiningCode);
    }
    return untersuchterAnalytDefiningCodeHashMap;
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
