# Copyright (c) 2020 Peter Wohlfarth (Appsfactory GmbH), Wladislaw Wagner (Vitasystems GmbH),
# Dave Petzold (Appsfactory GmbH) & Pauline Schulz (Appsfactory GmbH)
#
# This file is part of Project EHRbase
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.



*** Settings ***
Resource                ${EXECDIR}/robot/_resources/suite_settings.robot

Test Setup              generic.prepare new request session    Prefer=return=representation
...															   Authorization=${AUTHORIZATION['Authorization']}

Force Tags              observation_create    create



*** Variables ***




*** Test Cases ***
001 Create Body Temperature 
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-temp.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-temperature    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create body temperature  Body Temperature  create-body-temp.json    
    observation.validate response - 201


002 Create Blood Pressure 
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-blood-pressure.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	blood-pressure    valid

	ehr.create new ehr    					000_ehr_status.json
	observation.create blood pressure    	Blood Pressure    create-blood-pressure.json
    observation.validate response - 201


003 Create FIO2 
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-fio2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	fio2    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create FIO2    create-fio2.json
    observation.validate response - 201


004 Create Heart Rate 
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-heart-rate.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	heart-rate    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create heart rate    Heart Rate    create-heart-rate.json
    observation.validate response - 201


005 Create Sofa Score
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	sofa-score    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create sofa score    create-sofa-score.json
    observation.validate response - 201


006 Create Observation Lab
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create observation lab    create-observation-lab.json
    observation.validate response - 201


007 Create Coronavirus Lab Result
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-coronavirus-nachweis-test.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	coronavirus-lab-result    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create observation    create-coronavirus-nachweis-test.json
    observation.validate response - 201


008 Create Body Height
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-height.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-height    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create body height    Body Height    create-body-height.json
	observation.validate response - 201


009 Create Pregnancy Status
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pregnancy-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	pregnancy-status    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create pregnancy status    create-pregnancy-status.json
  	observation.validate response - 201


010 Create Frailty Scale Score
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-clinical-frailty-scale-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	frailty-scale-score    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create frailty scale score    create-clinical-frailty-scale-score.json
  	observation.validate response - 201


011 Create Smoking Status
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-smoking-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	smoking-status    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create smoking status    create-smoking-status.json
  	observation.validate response - 201


012 Create Body Weight
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-weight.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-weight    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create body weight  Body Weight  create-body-weight.json    
  	observation.validate response - 201


013 Create Patient in ICU
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-patient-in-icu.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	patient-in-icu    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create patient in icu    create-patient-in-icu.json
  	observation.validate response - 201


014 Create Blood Gas Panel
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-blood-gas-panel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	blood-gas-panel    valid   not-ready    not-implemented

	ehr.create new ehr    000_ehr_status.json
	observation.create blood gas panel  Blood gas Panel  create-blood-gas-panel.json
	observation.validate response - 201


015 Create Oxygen Saturation in Arterial Blood
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-oxygen-saturation.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	oxygen-saturation   valid    not-ready    not-implemented

	ehr.create new ehr    000_ehr_status.json
	observation.create oxygen saturation    create-oxygen-saturation.json
  	observation.validate response - 201


016 Create History of Travel
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-history-of-travel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	history-of-travel   valid    not-ready    not-implemented

	ehr.create new ehr    000_ehr_status.json
	observation.create history of travel    create-history-of-travel.json
  	observation.validate response - 201


017 Create Sex assigned at Birth (Divers)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-sex-assigned-at-birth-divers.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	sex-assigned-at-birth   valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create sex assigned at birth    Sex assigned at Birth (Divers)    create-sex-assigned-at-birth-divers.json
    observation.validate response - 201


018 Create Sex assigned at Birth (Female)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-sex-assigned-at-birth-female.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	sex-assigned-at-birth   valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create sex assigned at birth    Sex assigned at Birth (Female)    create-sex-assigned-at-birth-female.json
    observation.validate response - 201


019 Create Sex assigned at Birth (Male)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-sex-assigned-at-birth-male.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	sex-assigned-at-birth   valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create sex assigned at birth    Sex assigned at Birth (Male)    create-sex-assigned-at-birth-male.json
    observation.validate response - 201


020 Create Sex assigned at Birth (Unspecified)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-sex-assigned-at-birth-unspecified.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	sex-assigned-at-birth   valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create sex assigned at birth    Sex assigned at Birth (Unspecified)    create-sex-assigned-at-birth-unspecified.json
    observation.validate response - 201


021 Create Sex assigned at Birth (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-sex-assigned-at-birth-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	sex-assigned-at-birth   valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create sex assigned at birth    Sex assigned at Birth (Unknown)    create-sex-assigned-at-birth-unknown.json
    observation.validate response - 201


022 Create Study Inclusion due to Covid-19 confirmed Diagnosis (Present)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-study-inclusion-due-to-covid-19-confirmed-diagnosis-present.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	study-inclusion-due-to-covid-19   valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create study inclusion due to covid 19    Study Inclusion due to Covid-19 confirmed Diagnosis (Present)    create-study-inclusion-due-to-covid-19-confirmed-diagnosis-present.json
    observation.validate response - 201


023 Create Study Inclusion due to Covid-19 confirmed Diagnosis (Absent)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-study-inclusion-due-to-covid-19-confirmed-diagnosis-absent.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	study-inclusion-due-to-covid-19   valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create study inclusion due to covid 19    Study Inclusion due to Covid-19 confirmed Diagnosis (Absent)    create-study-inclusion-due-to-covid-19-confirmed-diagnosis-absent.json
    observation.validate response - 201


024 Create Study Inclusion due to Covid-19 confirmed Diagnosis (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-study-inclusion-due-to-covid-19-confirmed-diagnosis-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	study-inclusion-due-to-covid-19   valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create study inclusion due to covid 19    Study Inclusion due to Covid-19 confirmed Diagnosis (Unknown)    create-study-inclusion-due-to-covid-19-confirmed-diagnosis-unknown.json
    observation.validate response - 201


025 Create Interventional Clinical Trial Participation (Present with EudraCT)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-interventional-clinical-trial-participation-present-with-EudraCT.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	interventional-clinical-trial-participation   valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create interventional clinical trial participation    Interventional Clinical Trial Participation (Present with EudraCT)    create-interventional-clinical-trial-participation-present-with-EudraCT.json
    observation.validate response - 201


026 Create Interventional Clinical Trial Participation (Present with NCT)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-interventional-clinical-trial-participation-present-with-NCT.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	interventional-clinical-trial-participation   valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create interventional clinical trial participation    Interventional Clinical Trial Participation (Present with NCT)    create-interventional-clinical-trial-participation-present-with-NCT.json
    observation.validate response - 201


027 Create Interventional Clinical Trial Participation (Present with EudraCT AND NCT)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-interventional-clinical-trial-participation-present-with-EudraCT-AND-NCT.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	interventional-clinical-trial-participation   valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create interventional clinical trial participation    Interventional Clinical Trial Participation (Present with EudraCT AND NCT)    create-interventional-clinical-trial-participation-present-with-EudraCT-AND-NCT.json
    observation.validate response - 201


028 Create Interventional Clinical Trial Participation (Absent)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-interventional-clinical-trial-participation-absent.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	interventional-clinical-trial-participation   valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create interventional clinical trial participation    Interventional Clinical Trial Participation (Absent)    create-interventional-clinical-trial-participation-absent.json
    observation.validate response - 201


029 Create Interventional Clinical Trial Participation (Not Applicable)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-interventional-clinical-trial-participation-not-applicable.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	interventional-clinical-trial-participation   valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create interventional clinical trial participation    Interventional Clinical Trial Participation (Not Applicable)    create-interventional-clinical-trial-participation-not-applicable.json
    observation.validate response - 201


030 Create Interventional Clinical Trial Participation (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-interventional-clinical-trial-participation-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	interventional-clinical-trial-participation   valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create interventional clinical trial participation    Interventional Clinical Trial Participation (Unknown)    create-interventional-clinical-trial-participation-unknown.json
    observation.validate response - 201


031 Create Known Exposure to Covid-19 (Present)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-known-exposure-to-covid-19-present.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	known-exposure-to-covid-19    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create known exposure to covid 19    Known Exposure to Covid-19 (Present)    create-known-exposure-to-covid-19-present.json
    observation.validate response - 201


032 Create Known Exposure to Covid-19 (Absent)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-known-exposure-to-covid-19-absent.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	known-exposure-to-covid-19    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create known exposure to covid 19    Known Exposure to Covid-19 (Absent)    create-known-exposure-to-covid-19-absent.json
    observation.validate response - 201


033 Create Known Exposure to Covid-19 (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-known-exposure-to-covid-19-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	known-exposure-to-covid-19    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create known exposure to covid 19    Known Exposure to Covid-19 (Unknown)    create-known-exposure-to-covid-19-unknown.json
    observation.validate response - 201


034 Create PaO2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-PaO2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	PaO2   valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create PaO2  PaO2  create-pao2.json
    observation.validate response - 201


035 Create PaCO2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-PaCO2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	PaCO2   valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create PaCO2  PaCO2  create-paco2.json
    observation.validate response - 201


036 Create Respiratory Rate
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-rate.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	respiratory-rate   valid

    ehr.create new ehr    000_ehr_status.json
    observation.create respiratory rate  Respiratory Rate  create-respiratory-rate.json
    observation.validate response - 201


037 Create Observation Lab - CRP
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-CRP.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    CRP    valid    not-ready    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab CRP    Observation Lab - CRP    create-observation-lab-CRP.json
	observation.validate response - 201


038 Create Observation Lab - Bilirubin
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-bilirubin.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    bilirubin    valid    not-ready    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab bilirubin    Observation Lab - Bilirubin    create-observation-lab-bilirubin.json
    observation.validate response - 201


039 Create Observation Lab - Ferritin
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-ferritin.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    ferritin    valid    not-ready    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab ferritin    Observation Lab - Ferritin    create-observation-lab-ferritin.json
    observation.validate response - 201


040 Create Observation Lab - D-Dimer
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-d-dimer.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    d-dimer    valid    not-ready    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab d-dimer    Observation Lab - D-Dimer    create-observation-lab-d-dimer.json
    observation.validate response - 201


041 Create Observation Lab - Gamma Glutamyl Transferase
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-gamma-glutamyl-transferase.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    gamma-glutamyl-transferase    valid    not-ready    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab gamma glutamyl transferase    Observation Lab - Gamma Glutamyl Transferase    create-observation-lab-gamma-glutamyl-transferase.json
    observation.validate response - 201


042 Create Observation Lab - Lactate
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-lactate.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    lactate    valid    not-ready    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab lactate    Observation Lab - Lactate   create-observation-lab-lactate.json
    observation.validate response - 201


043 Create Observation Lab - Leukocytes
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-leukocytes.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    leukocytes    valid    not-ready    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab leukocytes    Observation Lab - Leukocytes   create-observation-lab-leukocytes.json
    observation.validate response - 201


044 Create Observation Lab - Lymphocytes
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-lymphocytes.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    lymphocytes    valid    not-ready    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab lymphocytes    Observation Lab - Lymphocytes   create-observation-lab-lymphocytes.json
    observation.validate response - 201


045 Create Observation Lab - Neutrophils
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-neutrophils.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    neutrophils    valid    not-ready    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab neutrophils    Observation Lab - Neutrophils   create-observation-lab-neutrophils.json
    observation.validate response - 201


046 Create Observation Lab - Hemoglobin
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-hemoglobin.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    hemoglobin    valid    not-ready    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab Hemoglobin  Hemoglobin  create-observation-lab-hemoglobin.json
    observation.validate response - 201


047 Create pH of Arterial blood
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-ph-of-arterial-blood.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	pH    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    observation.create pH    pH of Arterial blood    create-ph-of-arterial-blood.json
    observation.validate response - 201


048 Create Observation Lab - Cardiac Troponin
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-cardiac-troponin.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    cardiac-troponin    valid    not-ready    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab cardiac troponin  Observation Lab - Cardiac Troponin  create-observation-lab-cardiac-troponin.json
    observation.validate response - 201


049 Create Observation Lab - Creatinine
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-creatinine.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    creatinine    valid    not-ready    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab creatinine  Creatinine  create-observation-lab-creatinine.json
    observation.validate response - 201


050 Create Observation Lab - Lactate Dehydrogenase
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-lactate-dehydrogenase.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    lactate-dehydrogenase    valid    not-ready    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab lactate dehydrogenase  Lactate Hydrogenase  create-observation-lab-lactate-dehydrogenase.json    
    observation.validate response - 201


051 Create Observation Lab - Procalcitonin
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-procalcitonin.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    procalcitonin    valid    not-ready    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab procalcitonin  Procalcitonin  create-observation-lab-procalcitonin.json
    observation.validate response - 201


052 Create Observation Lab - Interleukin 6
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-interleukin-6.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    interleukin6    valid    not-ready    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab interleukin 6  Interleukin 6  create-observation-lab-interleukin-6.json
    observation.validate response - 201


053 Create Observation Lab - Natriuretic Peptide.b Prohormone N-Terminal
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-natriuretic-peptide.b-prohormone-n-terminal.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    natriuretic-peptide.b-prohormone-n-terminal    valid    not-ready    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab natriuretic peptide.b prohormone n-terminal  Natriuretic Peptide.b Prohormone N-Terminal  create-observation-lab-natriuretic-peptide.b-prohormone-n-terminal.json
    observation.validate response - 201


054 Create Observation Lab - Partial thromboplastin time
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-partial-thromboplastin-time.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    valid    not-ready    partial-thromboplastin-time    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab partial thromboplastin time  Partial Thromboplastin Time  create-observation-lab-partial-thromboplastin-time.json
    observation.validate response - 201


055 Create Observation Lab - Albumin in serum
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-albumin-in.serum.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    valid    not-ready    albumin-in-serum    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab albumin in serum  Albumin in Serum  create-observation-lab-albumin-in.serum.json
    observation.validate response - 201


056 Create Observation Lab - Platelets
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-platelets.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    valid    not-ready    platelets    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab platelets  Platelets  create-observation-lab-platelets.json
    observation.validate response - 201


057 Create Observation Lab - Antithrombin
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-antithrombin.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    valid    not-ready    antithrombin    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab antithrombin  Antithrombin  create-observation-lab-antithrombin.json
    observation.validate response - 201


058 Create Observation Lab - Fibrinogen
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-fibrinogen.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    valid    not-ready    fibrinogen    bug
	
    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab fibrinogen  Fibrinogen  create-observation-lab-fibrinogen.json
    observation.validate response - 201


059 Create Observation Lab - INR
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-inr.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    valid    not-ready    inr    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab inr  INR  create-observation-lab-inr.json
    observation.validate response - 201	


060 Create Observation Lab - Aspartate aminotransferase
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab-aspartate-aminotransferase.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	observation-lab    valid    not-ready    aspartate-aminotransferase    bug

    ehr.create new ehr    000_ehr_status.json
    observation.create observation lab aspartate aminotransferase  Aspartate Aminotransferase  create-observation-lab-aspartate-aminotransferase.json
    observation.validate response - 201	
