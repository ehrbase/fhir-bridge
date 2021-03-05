package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.Boolean;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class PersonennameClusterContainment extends Containment {
  public SelectAqlField<PersonennameCluster> PERSONENNAME_CLUSTER = new AqlFieldImp<PersonennameCluster>(PersonennameCluster.class, "", "PersonennameCluster", PersonennameCluster.class, this);

  public SelectAqlField<NamensartDefiningCode> NAMENSART_DEFINING_CODE = new AqlFieldImp<NamensartDefiningCode>(PersonennameCluster.class, "/items[at0006]/value|defining_code", "namensartDefiningCode", NamensartDefiningCode.class, this);

  public SelectAqlField<NullFlavour> NAMENSART_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PersonennameCluster.class, "/items[at0006]/null_flavour|defining_code", "namensartNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Boolean> BEVORZUGTER_NAME_VALUE = new AqlFieldImp<Boolean>(PersonennameCluster.class, "/items[at0022]/value|value", "bevorzugterNameValue", Boolean.class, this);

  public SelectAqlField<NullFlavour> BEVORZUGTER_NAME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PersonennameCluster.class, "/items[at0022]/null_flavour|defining_code", "bevorzugterNameNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> NAME_UNSTRUKTURIERT_VALUE = new AqlFieldImp<String>(PersonennameCluster.class, "/items[at0001]/value|value", "nameUnstrukturiertValue", String.class, this);

  public SelectAqlField<NullFlavour> NAME_UNSTRUKTURIERT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PersonennameCluster.class, "/items[at0001]/null_flavour|defining_code", "nameUnstrukturiertNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> TITEL_VALUE = new AqlFieldImp<String>(PersonennameCluster.class, "/items[at0002]/items[at0017]/value|value", "titelValue", String.class, this);

  public SelectAqlField<NullFlavour> TITEL_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PersonennameCluster.class, "/items[at0002]/items[at0017]/null_flavour|defining_code", "titelNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> VORNAME_VALUE = new AqlFieldImp<String>(PersonennameCluster.class, "/items[at0002]/items[at0003]/value|value", "vornameValue", String.class, this);

  public SelectAqlField<NullFlavour> VORNAME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PersonennameCluster.class, "/items[at0002]/items[at0003]/null_flavour|defining_code", "vornameNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<PersonennameWeitererVornameElement> WEITERER_VORNAME = new ListAqlFieldImp<PersonennameWeitererVornameElement>(PersonennameCluster.class, "/items[at0002]/items[at0004]", "weitererVorname", PersonennameWeitererVornameElement.class, this);

  public SelectAqlField<String> NACHNAME_VALUE = new AqlFieldImp<String>(PersonennameCluster.class, "/items[at0002]/items[at0005]/value|value", "nachnameValue", String.class, this);

  public SelectAqlField<NullFlavour> NACHNAME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PersonennameCluster.class, "/items[at0002]/items[at0005]/null_flavour|defining_code", "nachnameNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> SUFFIX_VALUE = new AqlFieldImp<String>(PersonennameCluster.class, "/items[at0002]/items[at0018]/value|value", "suffixValue", String.class, this);

  public SelectAqlField<NullFlavour> SUFFIX_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PersonennameCluster.class, "/items[at0002]/items[at0018]/null_flavour|defining_code", "suffixNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> UPPER_VALUE = new AqlFieldImp<TemporalAccessor>(PersonennameCluster.class, "/items[at0014]/value/upper|value", "upperValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> LOWER_VALUE = new AqlFieldImp<TemporalAccessor>(PersonennameCluster.class, "/items[at0014]/value/lower|value", "lowerValue", TemporalAccessor.class, this);

  public SelectAqlField<Boolean> LOWER_INCLUDED = new AqlFieldImp<Boolean>(PersonennameCluster.class, "/items[at0014]/value/lower_included", "lowerIncluded", Boolean.class, this);

  public SelectAqlField<Boolean> UPPER_INCLUDED = new AqlFieldImp<Boolean>(PersonennameCluster.class, "/items[at0014]/value/upper_included", "upperIncluded", Boolean.class, this);

  public SelectAqlField<NullFlavour> GUELTIGKEITSZEITRAUM_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PersonennameCluster.class, "/items[at0014]/null_flavour|defining_code", "gueltigkeitszeitraumNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(PersonennameCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private PersonennameClusterContainment() {
    super("openEHR-EHR-CLUSTER.person_name.v0");
  }

  public static PersonennameClusterContainment getInstance() {
    return new PersonennameClusterContainment();
  }
}
