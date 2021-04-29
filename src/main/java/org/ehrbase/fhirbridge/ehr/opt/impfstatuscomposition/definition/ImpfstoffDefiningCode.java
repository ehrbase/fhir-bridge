package org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ImpfstoffDefiningCode implements EnumValueSet {
  TYPHUS_VACCINE_PRODUCT("Typhus vaccine (product)", "", "local_terms", "37146000"),

  VACCINE_PRODUCT_CONTAINING_ONLY_HAEMOPHILUS_INFLUENZAE_TYPE_B_AND_NEISSERIA_MENINGITIDIS_SEROGROUP_C_ANTIGENS_MEDICINAL_PRODUCT("Vaccine product containing only Haemophilus influenzae type B and Neisseria meningitidis serogroup C antigens (medicinal product)", "", "local_terms", "836500008"),

  HEPATITIS_A_VIRUS_VACCINE("Hepatitis A virus vaccine", "", "local_terms", "14745005"),

  POLIOMYELITIS_ORAL_TRIVALENT_LEBEND_ABGESCHWAECHT("Poliomyelitis, oral, trivalent, lebend abgeschwächt", "", "local_terms", "J07BF02"),

  HAEMOPHILUS_INFLUENZAE_TYPE_B_RECOMBINANT_HEPATITIS_B_VIRUS_VACCINE_PRODUCT("Haemophilus influenzae Type b + recombinant hepatitis B virus vaccine (product)", "", "local_terms", "426971004"),

  VACCINE_PRODUCT_CONTAINING_SALMONELLA_ENTERICA_SUBSPECIES_ENTERICA_SEROVAR_TYPHI_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Salmonella enterica subspecies enterica serovar Typhi antigen (medicinal product)", "", "local_terms", "836390004"),

  DIPHTHERIA_TETANUS_PERTUSSIS_POLIOMYELITIS_RECOMBINANT_HEPATITIS_B_VIRUS_VACCINE_PRODUCT("Diphtheria + tetanus + pertussis + poliomyelitis + recombinant hepatitis B virus vaccine (product)", "", "local_terms", "427542001"),

  VACCINE_PRODUCT_CONTAINING_HEPATITIS_B_VIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Hepatitis B virus antigen (medicinal product)", "", "local_terms", "836374004"),

  VACCINE_PRODUCT_PRODUCT_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_CHOLERA_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_TYPHOID_VACCINE_SUBSTANCE("Vaccine product (product): { Has active ingredient (attribute) = Cholera vaccine (substance) } { Has active ingredient (attribute) = Typhoid vaccine (substance) }", "", "local_terms", "787859002:{127489000=396422009}{127489000=396441007}"),

  TOLLWUT_IMPFSTOFFE("Tollwut-Impfstoffe", "", "local_terms", "J07BG"),

  HEPATITIS_A_B_VACCINE_PRODUCT("Hepatitis A+B vaccine (product)", "", "local_terms", "333702001"),

  PERTUSSIS_IMPFSTOFFE("Pertussis-Impfstoffe", "", "local_terms", "J07AJ"),

  VACCINE_PRODUCT_CONTAINING_VACCINIA_VIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Vaccinia virus antigen (medicinal product)", "", "local_terms", "836389008"),

  VACCINE_PRODUCT_CONTAINING_LIVE_ATTENUATED_MYCOBACTERIUM_BOVIS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing live attenuated Mycobacterium bovis antigen (medicinal product)", "", "local_terms", "836402002"),

  PERTUSSIS_GEREINIGTES_ANTIGEN_KOMBINATIONEN_MIT_TOXOIDEN("Pertussis, gereinigtes Antigen, Kombinationen mit Toxoiden", "", "local_terms", "J07AJ52"),

  IMMUNGLOBULINE_NORMAL_HUMAN("Immunglobuline, normal human", "", "local_terms", "J06BA"),

  DIPHTHERIA_VACCINE("Diphtheria vaccine", "", "local_terms", "428214002"),

  BRUCELLA_ANTIGEN("Brucella-Antigen", "", "local_terms", "J07AD01"),

  MENINGOKOKKEN_TETRAVALENT_A_C_Y_W135_GEREINIGTES_POLYSACCHARID_ANTIGEN("Meningokokken tetravalent (A, C, Y, W-135), gereinigtes Polysaccharid-Antigen", "", "local_terms", "J07AH04"),

  VACCINE_PRODUCT_CONTAINING_ONLY_LIVE_ATTENUATED_HUMAN_ALPHAHERPESVIRUS3_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing only live attenuated Human alphaherpesvirus 3 antigen (medicinal product)", "", "local_terms", "2221000221107"),

  VACCINE_PRODUCT_CONTAINING_YELLOW_FEVER_VIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Yellow fever virus antigen (medicinal product)", "", "local_terms", "836385002"),

  TUBERKULOSE_IMPFSTOFFE("Tuberkulose-Impfstoffe", "", "local_terms", "J07AN"),

  CYTOMEGALIEVIRUS_IMMUNGLOBULIN("Cytomegalievirus-Immunglobulin", "", "local_terms", "J06BB09"),

  VACCINE_PRODUCT_CONTAINING_HAEMOPHILUS_INFLUENZAE_TYPE_B_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_HUMAN_POLIOVIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Haemophilus influenzae type B antigen (medicinal product) + Vaccine product containing Human poliovirus antigen (medicinal product)", "", "local_terms", "836380007+1031000221108"),

  DIPHTHERIE_POLIOMYELITIS_TETANUS("Diphtherie-Poliomyelitis-Tetanus", "", "local_terms", "J07CA01"),

  ZOSTER_VIRUS_LEBEND_ABGESCHWAECHT("Zoster Virus, lebend abgeschwächt", "", "local_terms", "J07BK02"),

  POCKEN_IMPFSTOFF_LEBEND_MODIFIZIERT("Pocken-Impfstoff, lebend, modifiziert", "", "local_terms", "J07BX01"),

  MEASLES_MUMPS_RUBELLA_VARICELLA_VACCINE_PRODUCT("Measles + mumps + rubella + varicella vaccine (product)", "", "local_terms", "419550004"),

  PRODUCT_CONTAINING_VARICELLA_ZOSTER_VIRUS_ANTIBODY_MEDICINAL_PRODUCT("Product containing Varicella-zoster virus antibody (medicinal product)", "", "local_terms", "62294009"),

  ANTI_D_RH_IMMUNGLOBULIN("Anti-D(rh)-Immunglobulin", "", "local_terms", "J06BB01"),

  VACCINE_PRODUCT_CONTAINING_BORDETELLA_PERTUSSIS_AND_CLOSTRIDIUM_TETANI_AND_CORYNEBACTERIUM_DIPHTHERIAE_AND_HAEMOPHILUS_INFLUENZAE_TYPE_B_AND_HUMAN_POLIOVIRUS_ANTIGENS_MEDICINAL_PRODUCT("Vaccine product containing Bordetella pertussis and Clostridium tetani and Corynebacterium diphtheriae and Haemophilus influenzae type B and Human poliovirus antigens (medicinal product)", "", "local_terms", "838279002"),

  PRODUCT_CONTAINING_HEPATITIS_B_SURFACE_ANTIGEN_IMMUNOGLOBULIN_MEDICINAL_PRODUCT("Product containing Hepatitis B surface antigen immunoglobulin (medicinal product)", "", "local_terms", "9542007"),

  INFLUENZA_VIRUS_VACCINE("Influenza virus vaccine", "", "local_terms", "46233009"),

  MEASLES_AND_MUMPS_VACCINE_PRODUCT("Measles and mumps vaccine (product)", "", "local_terms", "785865001"),

  VARICELLA_ZOSTER_VACCINE("Varicella-zoster vaccine", "", "local_terms", "407737004"),

  DIPHTHERIE_HEPATITIS_B_PERTUSSIS_TETANUS("Diphtherie-Hepatitis B-Pertussis-Tetanus", "", "local_terms", "J07CA05"),

  VACCINE_PRODUCT_CONTAINING_HUMAN_ALPHAHERPESVIRUS3_AND_MEASLES_MORBILLIVIRUS_AND_MUMPS_ORTHORUBULAVIRUS_AND_RUBELLA_VIRUS_ANTIGENS_MEDICINAL_PRODUCT("Vaccine product containing Human alphaherpesvirus 3 and Measles morbillivirus and Mumps orthorubulavirus and Rubella virus antigens (medicinal product)", "", "local_terms", "838280004"),

  HAEMOPHILUS_INFLUENZAE_B_UND_HEPATITIS_B("Haemophilus influenzae B und Hepatitis B", "", "local_terms", "J07CA08"),

  MASERN_KOMBINATIONEN_MIT_ROETELN_LEBEND_ABGESCHWAECHT("Masern, Kombinationen mit Röteln, lebend abgeschwächt", "", "local_terms", "J07BD53"),

  TETANUS_TOXOID_KOMBINATIONEN_MIT_DIPHTHERIE_TOXOID("Tetanus-Toxoid, Kombinationen mit Diphtherie-Toxoid", "", "local_terms", "J07AM51"),

  PAPILLOMVIRUS_IMPFSTOFFE("Papillomvirus-Impfstoffe", "", "local_terms", "J07BM"),

  VACCINE_PRODUCT_CONTAINING_INFLUENZA_VIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Influenza virus antigen (medicinal product)", "", "local_terms", "836377006"),

  GELBFIEBER_IMPFSTOFFE("Gelbfieber-Impfstoffe", "", "local_terms", "J07BL"),

  MENINGOKOKKEN_C_GEREINIGTES_POLYSACCHARID_ANTIGEN_KONJUGIERT("Meningokokken C, gereinigtes Polysaccharid-Antigen, konjugiert", "", "local_terms", "J07AH07"),

  BRUCELLOSE_IMPFSTOFFE("Brucellose-Impfstoffe", "", "local_terms", "J07AD"),

  MEASLES_VACCINE("Measles vaccine", "", "local_terms", "386012008"),

  MASERN_LEBEND_ABGESCHWAECHT("Masern, lebend abgeschwächt", "", "local_terms", "J07BD01"),

  DIPHTHERIE_IMPFSTOFFE("Diphtherie-Impfstoffe", "", "local_terms", "J07AF"),

  PRODUCT_CONTAINING_HUMAN_ANTI_D_IMMUNOGLOBULIN_MEDICINAL_PRODUCT("Product containing human anti-D immunoglobulin (medicinal product)|", "", "local_terms", "786224004"),

  PRODUCT_CONTAINING_PALIVIZUMAB_MEDICINAL_PRODUCT("Product containing palivizumab (medicinal product)", "", "local_terms", "108725001"),

  IMMUNGLOBULINE_NORMAL_HUMAN_ZUR_INTRAVASALEN_ANWENDUNG("Immunglobuline, normal human, zur intravasalen Anwendung", "", "local_terms", "J06BA02"),

  PALIVIZUMAB("Palivizumab", "", "local_terms", "J06BB16"),

  POLIOMYELITIS_IMPFSTOFFE("Poliomyelitis-Impfstoffe", "", "local_terms", "J07BF"),

  CHOLERA_VACCINE("Cholera vaccine", "", "local_terms", "35736007"),

  PRODUCT_CONTAINING_NORMAL_IMMUNOGLOBULIN_HUMAN_MEDICINAL_PRODUCT("Product containing normal immunoglobulin human (medicinal product)", "", "local_terms", "714569001"),

  DIPHTHERIA_PERTUSSIS_TETANUS_VACCINE_PRODUCT("Diphtheria + pertussis + tetanus vaccine (product)", "", "local_terms", "421245007"),

  VACCINE_PRODUCT_CONTAINING_ROTAVIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Rotavirus antigen (medicinal product)", "", "local_terms", "836387005"),

  VARICELLA_ZOSTER_IMMUNGLOBULIN("Varicella/Zoster-Immunglobulin", "", "local_terms", "J06BB03"),

  VACCINE_PRODUCT_PRODUCT_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_HAEMOPHILUS_INFLUENZAE_TYPE_B_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_LIVE_POLIOVIRUS_VACCINE_SUBSTANCE("Vaccine product (product): { Has active ingredient (attribute) = Haemophilus influenzae type b vaccine (substance) } { Has active ingredient (attribute) = Live Poliovirus vaccine (substance) }", "", "local_terms", "787859002:{127489000=412374001}{127489000=396436004}"),

  RUBELLA_VACCINE("Rubella vaccine", "", "local_terms", "386013003"),

  CHOLERA_IMPFSTOFFE("Cholera-Impfstoffe", "", "local_terms", "J07AE"),

  DIPHTHERIA_TETANUS_PERTUSSIS_POLIOMYELITIS_HAEMOPHILUS_INFLUENZAE_B_VACCINE_PRODUCT("Diphtheria + tetanus + pertussis + poliomyelitis + haemophilus influenzae b vaccine (product)", "", "local_terms", "414004005"),

  PRODUCT_CONTAINING_RABIES_HUMAN_IMMUNE_GLOBULIN_MEDICINAL_PRODUCT("Product containing rabies human immune globulin (medicinal product)", "", "local_terms", "80834004"),

  ENCEPHALITIS_JAPANISCHE_LEBEND_ABGESCHWAECHT("Encephalitis, japanische, lebend abgeschwächt", "", "local_terms", "J07BA03"),

  MENINGOCOCCUS_VACCINE("Meningococcus vaccine", "", "local_terms", "423531006"),

  VACCINE_PRODUCT_CONTAINING_ONLY_SEVERE_ACUTE_RESPIRATORY_SYNDROME_CORONAVIRUS2_MESSENGER_RIBONUCLEIC_ACID_MEDICINAL_PRODUCT("Vaccine product containing only Severe acute respiratory syndrome coronavirus 2 messenger ribonucleic acid (medicinal product)", "", "local_terms", "1119349007"),

  VARICELLA_ZOSTER_IMPFSTOFFE("Varicella Zoster Impfstoffe", "", "local_terms", "J07BK"),

  VACCINE_PRODUCT_CONTAINING_HUMAN_POLIOVIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Human poliovirus antigen (medicinal product)", "", "local_terms", "1031000221108"),

  HUMANER_PAPILLOMVIRUS_IMPFSTOFF_TYPEN6111618("Humaner-Papillomvirus-Impfstoff (Typen 6,11,16,18)", "", "local_terms", "J07BM01"),

  INFLUENZA_LEBEND_ABGESCHWAECHT("Influenza, lebend abgeschwächt", "", "local_terms", "J07BB03"),

  PERTUSSIS_INAKTIVIERT_GANZE_ZELLE_KOMBINATIONEN_MIT_TOXOIDEN("Pertussis, inaktiviert, ganze Zelle, Kombinationen mit Toxoiden", "", "local_terms", "J07AJ51"),

  DIPHTHERIE_PERTUSSIS_POLIOMYELITIS_TETANUS_HEPATITIS_B("Diphtherie-Pertussis-Poliomyelitis-Tetanus-Hepatitis B", "", "local_terms", "J07CA12"),

  PERTUSSIS_VACCINE("Pertussis vaccine", "", "local_terms", "61602008"),

  MENINGOKOKKEN_A_GEREINIGTES_POLYSACCHARID_ANTIGEN_KONJUGIERT("Meningokokken A, gereinigtes Polysaccharid-Antigen, konjugiert", "", "local_terms", "J07AH10"),

  VACCINE_PRODUCT_CONTAINING_RABIES_LYSSAVIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Rabies lyssavirus antigen (medicinal product)", "", "local_terms", "836393002"),

  VACCINE_PRODUCT_CONTAINING_NEISSERIA_MENINGITIDIS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Neisseria meningitidis antigen (medicinal product)", "", "local_terms", "836401009"),

  MENINGOKOKKEN_A_GEREINIGTES_POLYSACCHARID_ANTIGEN("Meningokokken A, gereinigtes Polysaccharid-Antigen", "", "local_terms", "J07AH01"),

  DIPHTHERIA_TETANUS_PERTUSSIS_RECOMBINANT_HEPATITIS_B_VIRUS_VACCINE_PRODUCT("Diphtheria + tetanus + pertussis + recombinant hepatitis B virus vaccine (product)", "", "local_terms", "426081003"),

  VACCINE_PRODUCT_CONTAINING_MEASLES_MORBILLIVIRUS_AND_MUMPS_ORTHORUBULAVIRUS_ANTIGENS_MEDICINAL_PRODUCT("Vaccine product containing Measles morbillivirus and Mumps orthorubulavirus antigens (medicinal product)", "", "local_terms", "836499004"),

  VACCINE_PRODUCT_CONTAINING_ONLY_CORYNEBACTERIUM_DIPHTHERIAE_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_HAEMOPHILUS_INFLUENZAE_TYPE_B_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_BORDETELLA_PERTUSSIS_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_CLOSTRIDIUM_TETANI_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_HEPATITIS_B_VIRUS_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_ONLY_NEISSERIA_MENINGITIDIS_SEROGROUP_A_AND_C_ANTIGENS_MEDICINAL_PRODUCT("Vaccine product containing only Corynebacterium diphtheriae antigen (medicinal product) + Vaccine product containing Haemophilus influenzae type B antigen (medicinal product) + Vaccine product containing Bordetella pertussis antigen (medicinal product) + Vaccine product containing Clostridium tetani antigen (medicinal product) + Vaccine product containing Hepatitis B virus antigen (medicinal product) + Vaccine product containing only Neisseria meningitidis serogroup A and C antigens (medicinal product)", "", "local_terms", "871729003+836380007+601000221108+863911006+836374004+871871008"),

  MASERN_KOMBINATIONEN_MIT_MUMPS_UND_ROETELN_LEBEND_ABGESCHWAECHT("Masern, Kombinationen mit Mumps und Röteln, lebend abgeschwächt", "", "local_terms", "J07BD52"),

  PNEUMOCOCCAL_VACCINE("Pneumococcal vaccine", "", "local_terms", "333598008"),

  HAEMOPHILUS_INFLUENZAE_TYPE_B_VACCINE("Haemophilus influenzae Type b vaccine", "", "local_terms", "333680004"),

  PEST_INAKTIVIERT_GANZE_ZELLE("Pest, inaktiviert, ganze Zelle", "", "local_terms", "J07AK01"),

  VACCINE_PRODUCT_PRODUCT_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_DIPHTHERIA_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_HAEMOPHILUS_INFLUENZAE_TYPE_B_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_PERTUSSIS_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_TETANUS_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_HEPATITIS_B_VIRUS_VACCINE_SUBSTANCE("Vaccine product (product): { Has active ingredient (attribute) = Diphtheria vaccine (substance) } { Has active ingredient (attribute) = Haemophilus influenzae type b vaccine (substance) } { Has active ingredient (attribute) = Pertussis vaccine (substance) } { Has active ingredient (attribute) = Tetanus vaccine (substance) } { Has active ingredient (attribute) = Hepatitis B virus vaccine (substance) }", "", "local_terms", "787859002:{127489000=428126001}{127489000=412374001}{127489000=396433007}{127489000=412375000}{127489000=396424005}"),

  TYPHOID_VACCINE("Typhoid vaccine", "", "local_terms", "89428009"),

  MILZBRAND_IMPFSTOFFE("Milzbrand-Impfstoffe", "", "local_terms", "J07AC"),

  VACCINE_PRODUCT_CONTAINING_ONLY_CORYNEBACTERIUM_DIPHTHERIAE_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_RUBELLA_VIRUS_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_CLOSTRIDIUM_TETANI_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing only Corynebacterium diphtheriae antigen (medicinal product) + Vaccine product containing Rubella virus antigen (medicinal product) + Vaccine product containing Clostridium tetani antigen (medicinal product)", "", "local_terms", "871729003+836388000+863911006"),

  MUMPS_LIVE_VIRUS_VACCINE("Mumps live virus vaccine", "", "local_terms", "90043005"),

  VACCINE_PRODUCT_PRODUCT_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_RUBELLA_AND_MUMPS_VACCINE_SUBSTANCE("Vaccine product (product): Has active ingredient (attribute) = Rubella and mumps vaccine (substance)", "", "local_terms", "787859002:127489000=412300006"),

  HAEMOPHILUS_INFLUENZAE_B_KOMBINATIONEN_MIT_MENINGOKOKKEN_C_KONJUGIERT("Haemophilus influenzae B, Kombinationen mit Meningokokken C, konjugiert", "", "local_terms", "J07AG53"),

  VACCINE_PRODUCT_CONTAINING_HAEMOPHILUS_INFLUENZAE_TYPE_B_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_BORDETELLA_PERTUSSIS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Haemophilus influenzae type B antigen (medicinal product) + Vaccine product containing Bordetella pertussis antigen (medicinal product)", "", "local_terms", "836380007+601000221108"),

  VACCINE_PRODUCT_CONTAINING_MUMPS_ORTHORUBULAVIRUS_AND_RUBELLA_VIRUS_ANTIGENS_MEDICINAL_PRODUCT("Vaccine product containing Mumps orthorubulavirus and Rubella virus antigens (medicinal product)", "", "local_terms", "836376002"),

  VACCINE_PRODUCT_PRODUCT_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_MEASLES_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_RUBELLA_VACCINE_SUBSTANCE("Vaccine product (product): { Has active ingredient (attribute) = Measles vaccine (substance) } { Has active ingredient (attribute) = Rubella vaccine (substance) }", "", "local_terms", "787859002:{127489000=396427003}{127489000=396438003}"),

  HEPATITIS_A_TYPHOID_VACCINE_PRODUCT("Hepatitis A+typhoid vaccine (product)", "", "local_terms", "333707007"),

  HUMAN_PAPILLOMAVIRUS_VACCINE("Human papillomavirus vaccine", "", "local_terms", "424519000"),

  VACCINE_PRODUCT_CONTAINING_BORDETELLA_PERTUSSIS_AND_CLOSTRIDIUM_TETANI_AND_CORYNEBACTERIUM_DIPHTHERIAE_AND_HAEMOPHILUS_INFLUENZAE_TYPE_B_AND_HEPATITIS_B_VIRUS_AND_HUMAN_POLIOVIRUS_ANTIGENS_MEDICINAL_PRODUCT("Vaccine product containing Bordetella pertussis and Clostridium tetani and Corynebacterium diphtheriae and Haemophilus influenzae type B and Hepatitis B virus and Human poliovirus antigens (medicinal product)", "", "local_terms", "871896006"),

  KOMBINATIONEN("Kombinationen", "", "local_terms", "J07BC20"),

  BRUCELLA_VACCINE("Brucella vaccine", "", "local_terms", "7230005"),

  PRODUCT_CONTAINING_TETANUS_ANTITOXIN_MEDICINAL_PRODUCT("Product containing tetanus antitoxin (medicinal product)", "", "local_terms", "384706007"),

  VACCINE_PRODUCT_CONTAINING_STREPTOCOCCUS_PNEUMONIAE_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_HAEMOPHILUS_INFLUENZAE_TYPE_B_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Streptococcus pneumoniae antigen (medicinal product) + Vaccine product containing Haemophilus influenzae type B antigen (medicinal product)", "", "local_terms", "836398006+836380007"),

  VACCINE_PRODUCT_CONTAINING_RUBELLA_VIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Rubella virus antigen (medicinal product)", "", "local_terms", "836388000"),

  PERTUSSIS_GEREINIGTES_ANTIGEN("Pertussis, gereinigtes Antigen", "", "local_terms", "J07AJ02"),

  DIPHTHERIE_PERTUSSIS_POLIOMYELITIS_TETANUS("Diphtherie-Pertussis-Poliomyelitis-Tetanus", "", "local_terms", "J07CA02"),

  VARICELLA_ZOSTER_LIVE_ATTENUATED_VACCINE_PRODUCT("Varicella-zoster live attenuated vaccine (product)", "", "local_terms", "407746005"),

  BEZLOTOXUMAB("Bezlotoxumab", "", "local_terms", "J06BB21"),

  VACCINE_PRODUCT_CONTAINING_BORDETELLA_PERTUSSIS_AND_CLOSTRIDIUM_TETANI_AND_CORYNEBACTERIUM_DIPHTHERIAE_AND_HEPATITIS_B_VIRUS_AND_HUMAN_POLIOVIRUS_ANTIGENS_MEDICINAL_PRODUCT("Vaccine product containing Bordetella pertussis and Clostridium tetani and Corynebacterium diphtheriae and Hepatitis B virus and Human poliovirus antigens (medicinal product)", "", "local_terms", "871892008"),

  PERTUSSIS_INAKTIVIERT_GANZE_ZELLE("Pertussis, inaktiviert, ganze Zelle", "", "local_terms", "J07AJ01"),

  INFLUENZA_IMPFSTOFFE("Influenza-Impfstoffe", "", "local_terms", "J07BB"),

  TOLLWUT_INAKTIVIERT_GANZES_VIRUS("Tollwut, inaktiviert, ganzes Virus", "", "local_terms", "J07BG01"),

  TYPHUS_GEREINIGTES_POLYSACCHARID_ANTIGEN("Typhus, gereinigtes Polysaccharid-Antigen", "", "local_terms", "J07AP03"),

  DIPHTHERIA_TETANUS_POLIOMYELITIS_VACCINE_PRODUCT("Diphtheria + tetanus + poliomyelitis vaccine (product)", "", "local_terms", "414006007"),

  MENINGOKOKKEN_IMPFSTOFFE("Meningokokken-Impfstoffe", "", "local_terms", "J07AH"),

  PRODUCT_CONTAINING_CYTOMEGALOVIRUS_ANTIBODY_MEDICINAL_PRODUCT("Product containing Cytomegalovirus antibody (medicinal product)", "", "local_terms", "9778000"),

  POLIOMYELITIS_TRIVALENT_INAKTIVIERT_GANZES_VIRUS("Poliomyelitis, trivalent, inaktiviert, ganzes Virus", "", "local_terms", "J07BF03"),

  VACCINE_PRODUCT_CONTAINING_VIBRIO_CHOLERAE_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_SALMONELLA_ENTERICA_SUBSPECIES_ENTERICA_SEROVAR_TYPHI_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Vibrio cholerae antigen (medicinal product) + Vaccine product containing Salmonella enterica subspecies enterica serovar Typhi antigen (medicinal product)", "", "local_terms", "836383009+836390004"),

  TETANUS_TOXOID("Tetanus-Toxoid", "", "local_terms", "J07AM01"),

  VACCINE_PRODUCT_CONTAINING_MEASLES_MORBILLIVIRUS_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_RUBELLA_VIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Measles morbillivirus antigen (medicinal product) + Vaccine product containing Rubella virus antigen (medicinal product)", "", "local_terms", "836382004+836388000"),

  DIPHTHERIE_TOXOID("Diphtherie-Toxoid", "", "local_terms", "J07AF01"),

  VACCINE_PRODUCT_CONTAINING_HEPATITIS_A_AND_HEPATITIS_B_VIRUS_ANTIGENS_MEDICINAL_PRODUCT("Vaccine product containing Hepatitis A and Hepatitis B virus antigens (medicinal product)", "", "local_terms", "836493003"),

  ROETELN_KOMBINATIONEN_MIT_MUMPS_LEBEND_ABGESCHWAECHT("Röteln, Kombinationen mit Mumps, lebend abgeschwächt", "", "local_terms", "J07BJ51"),

  YELLOW_FEVER_VACCINE("Yellow fever vaccine", "", "local_terms", "56844000"),

  VACCINE_PRODUCT_CONTAINING_CLOSTRIDIUM_TETANI_AND_CORYNEBACTERIUM_DIPHTHERIAE_AND_HUMAN_POLIOVIRUS_ANTIGENS_MEDICINAL_PRODUCT("Vaccine product containing Clostridium tetani and Corynebacterium diphtheriae and Human poliovirus antigens (medicinal product)", "", "local_terms", "836505003"),

  MUMPS_LEBEND_ABGESCHWAECHT("Mumps, lebend abgeschwächt", "", "local_terms", "J07BE01"),

  VACCINE_PRODUCT_CONTAINING_STREPTOCOCCUS_PNEUMONIAE_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Streptococcus pneumoniae antigen (medicinal product)", "", "local_terms", "836398006"),

  MENINGOKOKKEN_B_AEUSSERE_VESIKELMEMBRAN_IMPFSTOFF("Meningokokken B, äußere Vesikelmembran-Impfstoff", "", "local_terms", "J07AH06"),

  ANTHRAX_VACCINE("Anthrax vaccine", "", "local_terms", "333521006"),

  HEPATITIS_A_INAKTIVIERT_GANZES_VIRUS("Hepatitis A, inaktiviert, ganzes Virus", "", "local_terms", "J07BC02"),

  CHOLERA_LEBEND_ABGESCHWAECHT("Cholera, lebend abgeschwächt", "", "local_terms", "J07AE02"),

  TYPHUS_KOMBINATIONEN_MIT_PARATYPHUSTYPEN("Typhus, Kombinationen mit Paratyphustypen", "", "local_terms", "J07AP10"),

  TUBERCULOSOS_VACCINE("Tuberculosos vaccine", "", "local_terms", "420538001"),

  VACCINE_PRODUCT_CONTAINING_BORDETELLA_PERTUSSIS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Bordetella pertussis antigen (medicinal product)", "", "local_terms", "601000221108"),

  VACCINE_PRODUCT_CONTAINING_CLOSTRIDIUM_TETANI_AND_CORYNEBACTERIUM_DIPHTHERIAE_ANTIGENS_MEDICINAL_PRODUCT("Vaccine product containing Clostridium tetani and Corynebacterium diphtheriae antigens (medicinal product)", "", "local_terms", "836502000"),

  DIPHTHERIE_HEPATITIS_B_TETANUS("Diphtherie-Hepatitis B-Tetanus", "", "local_terms", "J07CA07"),

  CHOLERA_KOMBINATIONEN_MIT_TYPHUS_IMPFSTOFF_INAKTIVIERT_GANZE_ZELLE("Cholera, Kombinationen mit Typhus-Impfstoff, inaktiviert, ganze Zelle", "", "local_terms", "J07AE51"),

  TYPHUS_EXANTHEMATICUS_IMPFSTOFF("Typhus (exanthematicus)-Impfstoff", "", "local_terms", "J07AR"),

  PNEUMOKOKKEN_GEREINIGTES_POLYSACCHARID_ANTIGEN_KONJUGIERT("Pneumokokken, gereinigtes Polysaccharid-Antigen, konjugiert", "", "local_terms", "J07AL02"),

  VACCINE_PRODUCT_PRODUCT_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_HAEMOPHILUS_INFLUENZAE_TYPE_B_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_PERTUSSIS_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_TOXOID_SUBSTANCE("Vaccine product (product): { Has active ingredient (attribute) = Haemophilus influenzae type b vaccine (substance) } { Has active ingredient (attribute) = Pertussis vaccine (substance) } { Has active ingredient (attribute) = Toxoid (substance) }", "", "local_terms", "787859002:{127489000=412374001}{127489000=396433007}{127489000=396411005}"),

  POLIOVIRUS_VACCINE("Poliovirus vaccine", "", "local_terms", "111164008"),

  HAEMOPHILUS_INFLUENZAE_B_GEREINIGTES_ANTIGEN_KONJUGIERT("Haemophilus influenzae B, gereinigtes Antigen konjugiert", "", "local_terms", "J07AG01"),

  TOLLWUT_IMMUNGLOBULIN("Tollwut-Immunglobulin", "", "local_terms", "J06BB05"),

  VACCINE_PRODUCT_PRODUCT_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_DIPHTHERIA_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_HAEMOPHILUS_INFLUENZAE_TYPE_B_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_PERTUSSIS_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_TETANUS_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_HEPATITIS_B_VIRUS_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_MENINGOCOCCUS_GROUP_A_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_MENINGOCOCCUS_GROUP_C_VACCINE_SUBSTANCE("Vaccine product (product): { Has active ingredient (attribute) = Diphtheria vaccine (substance) } { Has active ingredient (attribute) = Haemophilus influenzae type b vaccine (substance) } { Has active ingredient (attribute) = Pertussis vaccine (substance) } { Has active ingredient (attribute) = Tetanus vaccine (substance) } { Has active ingredient (attribute) = Hepatitis B virus vaccine (substance) } { Has active ingredient (attribute) = Meningococcus group A vaccine (substance) } { Has active ingredient (attribute) = Meningococcus group C vaccine (substance) }", "", "local_terms", "787859002:{127489000=428126001}{127489000=412374001}{127489000=396433007}{127489000=412375000}{127489000=396424005}{127489000=768365004}{127489000=768366003}"),

  DIPHTHERIE_HAEMOPHILUS_INFLUENZAE_B_PERTUSSIS_TETANUS_HEPATITIS_B("Diphtherie-Haemophilus influenzae B-Pertussis-Tetanus-Hepatitis B", "", "local_terms", "J07CA11"),

  IMMUNGLOBULINE_NORMAL_HUMAN_ZUR_EXTRAVASALEN_ANWENDUNG("Immunglobuline, normal human, zur extravasalen Anwendung", "", "local_terms", "J06BA01"),

  VACCINE_PRODUCT_CONTAINING_ONLY_LIVE_ATTENUATED_MUMPS_ORTHORUBULAVIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing only live attenuated Mumps orthorubulavirus antigen (medicinal product)", "", "local_terms", "871738001"),

  VARICELLA_LEBEND_ABGESCHWAECHT("Varicella, lebend abgeschwächt", "", "local_terms", "J07BK01"),

  VACCINE_PRODUCT_CONTAINING_ONLY_CORYNEBACTERIUM_DIPHTHERIAE_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_HAEMOPHILUS_INFLUENZAE_TYPE_B_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_BORDETELLA_PERTUSSIS_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_CLOSTRIDIUM_TETANI_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_HEPATITIS_B_VIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing only Corynebacterium diphtheriae antigen (medicinal product) + Vaccine product containing Haemophilus influenzae type B antigen (medicinal product) + Vaccine product containing Bordetella pertussis antigen (medicinal product) + Vaccine product containing Clostridium tetani antigen (medicinal product) + Vaccine product containing Hepatitis B virus antigen (medicinal product)", "", "local_terms", "871729003+836380007+601000221108+863911006+836374004"),

  ANDERE_MENINGOKOKKEN_POLYVALENT_GEREINIGTES_POLYSACCHARID_ANTIGEN("Andere Meningokokken polyvalent, gereinigtes Polysaccharid-Antigen", "", "local_terms", "J07AH05"),

  HAEMOPHILUS_INFLUENZAE_TYPE_B_MENINGOCOCCAL_GROUP_C_VACCINE_PRODUCT("Haemophilus influenzae type b + Meningococcal group C vaccine (product)", "", "local_terms", "423912009"),

  FSME_INAKTIVIERT_GANZES_VIRUS("FSME, inaktiviert, ganzes Virus", "", "local_terms", "J07BA01"),

  VACCINE_PRODUCT_PRODUCT_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_PNEUMOCOCCAL_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_HAEMOPHILUS_INFLUENZAE_TYPE_B_VACCINE_SUBSTANCE("Vaccine product (product): { Has active ingredient (attribute) = Pneumococcal vaccine (substance) } { Has active ingredient (attribute) = Haemophilus influenzae type b vaccine (substance) }", "", "local_terms", "78785900:{127489000=398730001}{127489000=412374001}"),

  VACCINE_PRODUCT_CONTAINING_HEPATITIS_A_VIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Hepatitis A virus antigen (medicinal product)", "", "local_terms", "836375003"),

  PEST_IMPFSTOFFE("Pest-Impfstoffe", "", "local_terms", "J07AK"),

  DIPHTHERIA_TETANUS_PERTUSSIS_POLIOMYELITIS_RECOMBINANT_HEPATITIS_B_VIRUS_RECOMBINANT_HAEMOPHILUS_INFLUENZAE_TYPE_B_VACCINE_PRODUCT("Diphtheria + tetanus + pertussis + poliomyelitis + recombinant hepatitis B virus + recombinant haemophilus influenzae type B vaccine (product)", "", "local_terms", "426842004"),

  ANDERE_MENINGOKOKKEN_MONOVALENT_GEREINIGTES_POLYSACCHARID_ANTIGEN("Andere Meningokokken monovalent, gereinigtes Polysaccharid-Antigen", "", "local_terms", "J07AH02"),

  CHOLERA_INAKTIVIERT_GANZE_ZELLE("Cholera, inaktiviert, ganze Zelle", "", "local_terms", "J07AE01"),

  VACCINE_PRODUCT_CONTAINING_HAEMOPHILUS_INFLUENZAE_TYPE_B_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Haemophilus influenzae type B antigen (medicinal product)", "", "local_terms", "836380007"),

  MUMPS_IMPFSTOFFE("Mumps-Impfstoffe", "", "local_terms", "J07BE"),

  TYPHUS_IMPFSTOFFE("Typhus-Impfstoffe", "", "local_terms", "J07AP"),

  VACCINE_PRODUCT_CONTAINING_BORDETELLA_PERTUSSIS_AND_CLOSTRIDIUM_TETANI_AND_CORYNEBACTERIUM_DIPHTHERIAE_AND_HUMAN_POLIOVIRUS_ANTIGENS_MEDICINAL_PRODUCT("Vaccine product containing Bordetella pertussis and Clostridium tetani and Corynebacterium diphtheriae and Human poliovirus antigens (medicinal product)", "", "local_terms", "836508001"),

  ENCEPHALITIS_IMPFSTOFFE("Encephalitis-Impfstoffe", "", "local_terms", "J07BA"),

  MASERN_KOMBINATIONEN_MIT_MUMPS_ROETELN_UND_VARICELLA_LEBEND_ABGESCHWAECHT("Masern, Kombinationen mit Mumps, Röteln und Varicella, lebend abgeschwächt", "", "local_terms", "J07BD54"),

  HEPATITIS_B_GEREINIGTES_ANTIGEN("Hepatitis B, gereinigtes Antigen", "", "local_terms", "J07BC01"),

  ROETELN_IMPFSTOFFE("Röteln-Impfstoffe", "", "local_terms", "J07BJ"),

  PRODUCT_CONTAINING_BEZLOTOXUMAB_MEDICINAL_PRODUCT("Product containing bezlotoxumab (medicinal product)", "", "local_terms", "763703008"),

  HUMANER_PAPILLOMVIRUS_IMPFSTOFF_TYPEN1618("Humaner-Papillomvirus-Impfstoff (Typen 16,18)", "", "local_terms", "J07BM02"),

  ENCEPHALITIS_JAPANISCHE_INAKTIVIERT_GANZES_VIRUS("Encephalitis, japanische, inaktiviert, ganzes Virus", "", "local_terms", "J07BA02"),

  DIPHTHERIE_HAEMOPHILUS_INFLUENZAE_B_PERTUSSIS_POLIOMYELITIS_TETANUS("Diphtherie-Haemophilus influenzae B-Pertussis-Poliomyelitis-Tetanus", "", "local_terms", "J07CA06"),

  TYPHUS_HEPATITIS_A("Typhus-Hepatitis A", "", "local_terms", "J07CA10"),

  MENINGOKOKKEN_B_MULTIKOMPONENTEN_IMPFSTOFF("Meningokokken B, Multikomponenten-Impfstoff", "", "local_terms", "J07AH09"),

  POLIOMYELITIS_ORAL_MONOVALENT_LEBEND_ABGESCHWAECHT("Poliomyelitis, oral, monovalent, lebend abgeschwächt", "", "local_terms", "J07BF01"),

  MEASLES_MUMPS_AND_RUBELLA_VACCINE_PRODUCT("Measles, mumps and rubella vaccine (product)", "", "local_terms", "61153008"),

  HEPATITIS_A_GEREINIGTES_ANTIGEN("Hepatitis A, gereinigtes Antigen", "", "local_terms", "J07BC03"),

  VACCINE_PRODUCT_CONTAINING_ONLY_SEVERE_ACUTE_RESPIRATORY_SYNDROME_CORONAVIRUS2_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing only Severe acute respiratory syndrome coronavirus 2 antigen (medicinal product)", "", "local_terms", "1119305005"),

  VACCINE_PRODUCT_CONTAINING_HUMAN_ALPHAHERPESVIRUS3_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Human alphaherpesvirus 3 antigen (medicinal product)", "", "local_terms", "836495005"),

  ROTAVIRUS_PENTAVALENT_LEBEND_REASSORTANTEN("Rotavirus, pentavalent, lebend, Reassortanten", "", "local_terms", "J07BH02"),

  INFLUENZA_INAKTIVIERT_SPALTVIRUS_ODER_OBERFLAECHENANTIGEN("Influenza, inaktiviert, Spaltvirus oder Oberflächenantigen", "", "local_terms", "J07BB02"),

  VACCINE_PRODUCT_CONTAINING_BORDETELLA_PERTUSSIS_AND_CLOSTRIDIUM_TETANI_AND_CORYNEBACTERIUM_DIPHTHERIAE_ANTIGENS_MEDICINAL_PRODUCT("Vaccine product containing Bordetella pertussis and Clostridium tetani and Corynebacterium diphtheriae antigens (medicinal product)", "", "local_terms", "836503005"),

  VACCINE_PRODUCT_PRODUCT_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_DIPHTHERIA_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_HEPATITIS_B_VIRUS_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_TETANUS_VACCINE_SUBSTANCE("Vaccine product (product): { Has active ingredient (attribute) = Diphtheria vaccine (substance) } { Has active ingredient (attribute) = Hepatitis B virus vaccine (substance) } { Has active ingredient (attribute) = Tetanus vaccine (substance) }", "", "local_terms", "787859002:{127489000=428126001}{127489000=396424005}{127489000=412375000}"),

  HAEMOPHILUS_INFLUENZAE_B_UND_POLIOMYELITIS("Haemophilus influenzae B und Poliomyelitis", "", "local_terms", "J07CA04"),

  ROTAVIRUS_LEBEND_ABGESCHWAECHT("Rotavirus, lebend abgeschwächt", "", "local_terms", "J07BH01"),

  VACCINE_PRODUCT_CONTAINING_ONLY_CORYNEBACTERIUM_DIPHTHERIAE_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_HEPATITIS_B_VIRUS_ANTIGEN_MEDICINAL_PRODUCT_VACCINE_PRODUCT_CONTAINING_CLOSTRIDIUM_TETANI_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing only Corynebacterium diphtheriae antigen (medicinal product) + Vaccine product containing Hepatitis B virus antigen (medicinal product) + Vaccine product containing Clostridium tetani antigen (medicinal product)", "", "local_terms", "871729003+836374004+863911006"),

  VACCINE_PRODUCT_CONTAINING_HEPATITIS_A_VIRUS_AND_SALMONELLA_ENTERICA_SUBSPECIES_ENTERICA_SEROVAR_TYPHI_ANTIGENS_MEDICINAL_PRODUCT("Vaccine product containing Hepatitis A virus and Salmonella enterica subspecies enterica serovar Typhi antigens (medicinal product)", "", "local_terms", "836501007"),

  TYPHUS_INAKTIVIERT_GANZE_ZELLE("Typhus, inaktiviert, ganze Zelle", "", "local_terms", "J07AP02"),

  TYPHUS_EXANTHEMATICUS_INAKTIVIERT_GANZE_ZELLE("Typhus exanthematicus, inaktiviert, ganze Zelle", "", "local_terms", "J07AR01"),

  VACCINE_PRODUCT_CONTAINING_BORDETELLA_PERTUSSIS_AND_CLOSTRIDIUM_TETANI_AND_CORYNEBACTERIUM_DIPHTHERIAE_AND_HEPATITIS_B_VIRUS_MEDICINAL_PRODUCT("Vaccine product containing Bordetella pertussis and Clostridium tetani and Corynebacterium diphtheriae and Hepatitis B virus (medicinal product)", "", "local_terms", "871917002"),

  SMALLPOX_VACCINE("Smallpox vaccine", "", "local_terms", "33234009"),

  VACCINE_PRODUCT_CONTAINING_JAPANESE_ENCEPHALITIS_VIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Japanese encephalitis virus antigen (medicinal product)", "", "local_terms", "836378001"),

  HAEMOPHILUS_INFLUENZAE_B_IMPFSTOFFE("Haemophilus influenzae B-Impfstoffe", "", "local_terms", "J07AG"),

  VACCINE_PRODUCT_PRODUCT("Vaccine product (product)", "", "local_terms", "787859002"),

  JAPANESE_B_ENCEPHALITIS_VACCINE("Japanese B encephalitis vaccine", "", "local_terms", "333697005"),

  HEPATITIS_B_IMMUNGLOBULIN("Hepatitis-B-Immunglobulin", "", "local_terms", "J06BB04"),

  PNEUMOKOKKEN_GEREINIGTES_POLYSACCHARID_ANTIGEN("Pneumokokken, gereinigtes Polysaccharid-Antigen", "", "local_terms", "J07AL01"),

  PNEUMOKOKKEN_GEREINIGTES_POLYSACCHARID_ANTIGEN_UND_HAEMOPHILUS_INFLUENZAE_B_KONJUGIERT("Pneumokokken, gereinigtes Polysaccharid-Antigen und Haemophilus influenzae B, konjugiert", "", "local_terms", "J07AL52"),

  INFLUENZA_INAKTIVIERT_GANZES_VIRUS("Influenza, inaktiviert, ganzes Virus", "", "local_terms", "J07BB01"),

  VACCINE_PRODUCT_CONTAINING_YERSINIA_PESTIS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Yersinia pestis antigen (medicinal product)", "", "local_terms", "840549009"),

  VACCINE_PRODUCT_PRODUCT_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_DIPHTHERIA_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_RUBELLA_VACCINE_SUBSTANCE_HAS_ACTIVE_INGREDIENT_ATTRIBUTE_TETANUS_VACCINE_SUBSTANCE("Vaccine product (product): { Has active ingredient (attribute) = Diphtheria vaccine (substance) } { Has active ingredient (attribute) = Rubella vaccine (substance) } { Has active ingredient (attribute) = Tetanus vaccine (substance) }", "", "local_terms", "787859002:{127489000=428126001}{127489000=396438003}{127489000=412375000}"),

  TETANUS_IMPFSTOFFE("Tetanus-Impfstoffe", "", "local_terms", "J07AM"),

  MENINGOKOKKEN_BIVALENT_A_C_GEREINIGTES_POLYSACCHARID_ANTIGEN("Meningokokken bivalent (A, C), gereinigtes Polysaccharid-Antigen", "", "local_terms", "J07AH03"),

  MASERN_IMPFSTOFFE("Masern-Impfstoffe", "", "local_terms", "J07BD"),

  TICK_BORNE_ENCEPHALITIS_VACCINE("Tick-borne encephalitis vaccine", "", "local_terms", "333699008"),

  RABIES_VACCINE("Rabies vaccine", "", "local_terms", "333606008"),

  ROTAVIRUS_VACCINE("Rotavirus vaccine", "", "local_terms", "116077000"),

  POLIOMYELITIS_ORAL_BIVALENT_LEBEND_ABGESCHWAECHT("Poliomyelitis, oral, bivalent, lebend abgeschwächt", "", "local_terms", "J07BF04"),

  DIPHTHERIE_HAEMOPHILUS_INFLUENZAE_B_PERTUSSIS_POLIOMYELITIS_TETANUS_HEPATITIS_B("Diphtherie-Haemophilus influenzae B-Pertussis-Poliomyelitis-Tetanus-Hepatitis B", "", "local_terms", "J07CA09"),

  DIPHTHERIA_TETANUS_PERTUSSIS_POLIOMYELITIS_VACCINE_PRODUCT("Diphtheria + tetanus + pertussis + poliomyelitis vaccine (product)", "", "local_terms", "414005006"),

  TUBERKULOSE_LEBEND_ABGESCHWAECHT("Tuberkulose, lebend abgeschwächt", "", "local_terms", "J07AN01"),

  TETANUS_IMMUNGLOBULIN("Tetanus-Immunglobulin", "", "local_terms", "J06BB02"),

  PLAGUE_VACCINE("Plague vaccine", "", "local_terms", "11866009"),

  ROTAVIRUS_DIARRHOE_IMPFSTOFFE("Rotavirus-Diarrhoe-Impfstoffe", "", "local_terms", "J07BH"),

  MASERN_KOMBINATIONEN_MIT_MUMPS_LEBEND_ABGESCHWAECHT("Masern, Kombinationen mit Mumps, lebend abgeschwächt", "", "local_terms", "J07BD51"),

  VACCINE_PRODUCT_CONTAINING_CLOSTRIDIUM_TETANI_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Clostridium tetani antigen (medicinal product)", "", "local_terms", "863911006"),

  HAEMOPHILUS_INFLUENZAE_B_KOMBINATIONEN_MIT_PERTUSSIS_UND_TOXOIDEN("Haemophilus influenzae B, Kombinationen mit Pertussis und Toxoiden", "", "local_terms", "J07AG52"),

  HUMANER_PAPILLOMVIRUS_IMPFSTOFF_TYPEN61116183133455258("Humaner-Papillomvirus-Impfstoff (Typen 6,11,16,18,31,33,45,52,58)", "", "local_terms", "J07BM03"),

  ZOSTER_VIRUS_GEREINIGTES_ANTIGEN("Zoster Virus, gereinigtes Antigen", "", "local_terms", "J07BK03"),

  DIPHTHERIE_HAEMOPHILUS_INFLUENZAE_B_PERTUSSIS_TETANUS_HEPATITIS_B_MENINGOKOKKEN_A_C("Diphtherie-Haemophilus influenzae B-Pertussis-Tetanus-Hepatitis B-Meningokokken A + C", "", "local_terms", "J07CA13"),

  VACCINE_PRODUCT_CONTAINING_HUMAN_PAPILLOMAVIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Human papillomavirus antigen (medicinal product)", "", "local_terms", "836379009"),

  MENINGOKOKKEN_TETRAVALENT_A_C_Y_W135_GEREINIGTES_POLYSACCHARID_ANTIGEN_KONJUGIERT("Meningokokken tetravalent (A, C, Y, W-135), gereinigtes Polysaccharid-Antigen, konjugiert", "", "local_terms", "J07AH08"),

  VACCINE_PRODUCT_CONTAINING_BACILLUS_ANTHRACIS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Bacillus anthracis antigen (medicinal product)", "", "local_terms", "836384003"),

  HEPATITIS_B_VIRUS_VACCINE("Hepatitis B virus vaccine", "", "local_terms", "34689006"),

  ANDERE_VIRALE_IMPFSTOFFE("Andere virale Impfstoffe", "", "local_terms", "J07BX"),

  VACCINE_PRODUCT_CONTAINING_VIBRIO_CHOLERAE_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Vibrio cholerae antigen (medicinal product)", "", "local_terms", "836383009"),

  DIPHTHERIA_TETANUS_VACCINE_PRODUCT("Diphtheria + tetanus vaccine (product)", "", "local_terms", "350327004"),

  ANTHRAX_ANTIGEN("Anthrax-Antigen", "", "local_terms", "J07AC01"),

  PNEUMOKOKKEN_IMPFSTOFFE("Pneumokokken-Impfstoffe", "", "local_terms", "J07AL"),

  VACCINE_PRODUCT_CONTAINING_MEASLES_MORBILLIVIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Measles morbillivirus antigen (medicinal product)", "", "local_terms", "836382004"),

  VACCINE_PRODUCT_CONTAINING_HAEMOPHILUS_INFLUENZAE_TYPE_B_AND_HEPATITIS_B_VIRUS_ANTIGENS_MEDICINAL_PRODUCT("Vaccine product containing Haemophilus influenzae type B and Hepatitis B virus antigens (medicinal product)", "", "local_terms", "865946000"),

  BAKTERIELLE_UND_VIRALE_IMPFSTOFFE_KOMBINIERT("Bakterielle und virale Impfstoffe, kombiniert", "", "local_terms", "J07CA"),

  GELBFIEBER_LEBEND_ABGESCHWAECHT("Gelbfieber, lebend abgeschwächt", "", "local_terms", "J07BL01"),

  VACCINE_PRODUCT_CONTAINING_CORYNEBACTERIUM_DIPHTHERIAE_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Corynebacterium diphtheriae antigen (medicinal product)", "", "local_terms", "836381006"),

  DIPHTHERIE_ROETELN_TETANUS("Diphtherie-Röteln-Tetanus", "", "local_terms", "J07CA03"),

  HEPATITIS_IMPFSTOFFE("Hepatitis-Impfstoffe", "", "local_terms", "J07BC"),

  HAEMOPHILUS_INFLUENZAE_B_KOMBINATIONEN_MIT_TOXOIDEN("Haemophilus influenzae B, Kombinationen mit Toxoiden", "", "local_terms", "J07AG51"),

  SPEZIFISCHE_IMMUNGLOBULINE("Spezifische Immunglobuline", "", "local_terms", "J06BB"),

  VACCINE_PRODUCT_CONTAINING_TICK_BORNE_ENCEPHALITIS_VIRUS_ANTIGEN_MEDICINAL_PRODUCT("Vaccine product containing Tick-borne encephalitis virus antigen (medicinal product)", "", "local_terms", "836403007"),

  VACCINE_PRODUCT_CONTAINING_MEASLES_MORBILLIVIRUS_AND_MUMPS_ORTHORUBULAVIRUS_AND_RUBELLA_VIRUS_ANTIGENS_MEDICINAL_PRODUCT("Vaccine product containing Measles morbillivirus and Mumps orthorubulavirus and Rubella virus antigens (medicinal product)", "", "local_terms", "836494009"),

  TYPHUS_ORAL_LEBEND_ABGESCHWAECHT("Typhus, oral, lebend abgeschwächt", "", "local_terms", "J07AP01"),

  ROETELN_LEBEND_ABGESCHWAECHT("Röteln, lebend abgeschwächt", "", "local_terms", "J07BJ01"),

  TETANUS_TOXOID_KOMBINATIONEN_MIT_TETANUS_IMMUNGLOBULIN("Tetanus-Toxoid, Kombinationen mit Tetanus-Immunglobulin", "", "local_terms", "J07AM52"),

  TETANUS_VACCINE("Tetanus vaccine", "", "local_terms", "333621002");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ImpfstoffDefiningCode(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public static Map<String, ImpfstoffDefiningCode> getCodesAsMap(){
    Map<String, ImpfstoffDefiningCode> impfstoffDefiningCodeHashMap = new HashMap<>();
    for (ImpfstoffDefiningCode impfstoffDefiningCode : ImpfstoffDefiningCode.values()) {
      impfstoffDefiningCodeHashMap.put(impfstoffDefiningCode.getCode(), impfstoffDefiningCode);
    }
    return impfstoffDefiningCodeHashMap;
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
