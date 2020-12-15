package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ProblemDiagnoseDefiningcode implements EnumValueSet {
  K289("K28.9", "K28.9", "ICD", "K28.9"),

  B20("B20", "B20", "ICD", "B20"),

  I829("I82.9", "I82.9", "ICD", "I82.9"),

  I64("I64", "I64", "ICD", "I64"),

  J189("J18.9", "J18.9", "ICD", "J18.9"),

  I2529("I25.29", "I25.29", "ICD", "I25.29"),

  A419("A41.9", "A41.9", "ICD", "A41.9"),

  I251("I25.1", "I25.1", "ICD", "I25.1"),

  Z941("Z94.1", "Z94.1", "ICD", "Z94.1"),

  Z9588("Z95.88", "Z95.88", "ICD", "Z95.88"),

  Z9480("Z94.80", "Z94.80", "ICD", "Z94.80"),

  I219("I21.9", "I21.9", "ICD", "I21.9"),

  N179("N17.9", "N17.9", "ICD", "N17.9"),

  B21("B21", "B21", "ICD", "B21"),

  Z949("Z94.9", "Z94.9", "ICD", "Z94.9"),

  I499("I49.9", "I49.9", "ICD", "I49.9"),

  Z9488("Z94.88", "Z94.88", "ICD", "Z94.88"),

  Z947("Z94.7", "Z94.7", "ICD", "Z94.7"),

  Z945("Z94.5", "Z94.5", "ICD", "Z94.5"),

  Z943("Z94.3", "Z94.3", "ICD", "Z94.3"),

  I652("I65.2", "I65.2", "ICD", "I65.2"),

  I269("I26.9", "I26.9", "ICD", "I26.9"),

  Z955("Z95.5", "Z95.5", "ICD", "Z95.5"),

  I509("I50.9", "I50.9", "ICD", "I50.9"),

  Z951("Z95.1", "Z95.1", "ICD", "Z95.1"),

  I1090("I10.90", "I10.90", "ICD", "I10.90"),

  Z940("Z94.0", "Z94.0", "ICD", "Z94.0"),

  Z9481("Z94.81", "Z94.81", "ICD", "Z94.81"),

  B22("B22", "B22", "ICD", "B22"),

  I739("I73.9", "I73.9", "ICD", "I73.9"),

  B24("B24", "B24", "ICD", "B24"),

  Z946("Z94.6", "Z94.6", "ICD", "Z94.6"),

  Z944("Z94.4", "Z94.4", "ICD", "Z94.4"),

  Z942("Z94.2", "Z94.2", "ICD", "Z94.2");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ProblemDiagnoseDefiningcode(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
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
