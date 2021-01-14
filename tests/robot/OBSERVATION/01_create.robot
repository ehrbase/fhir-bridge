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
...															   Authorization=Basic bXl1c2VyOm15UGFzc3dvcmQ0MzI=

Force Tags              create



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
	observation.create body temperature    create-body-temp.json
    observation.validate response - 201


002 Create Blood Pressure 
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-blood-pressure.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	blood-pressure    valid

	ehr.create new ehr    					000_ehr_status.json
	observation.create blood pressure    	create-blood-pressure.json
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
	observation.create heart rate    create-heart-rate.json
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


007 Create Observation Using Default Profile
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-with-default-profile.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status \n\n
    ...                 6. *VALIDATE* outcome against diagnostic text
	[Tags]             	invalid

	ehr.create new ehr    000_ehr_status.json
	observation.create observation    create-observation-with-default-profile.json
    observation.validate response - 422 (default profile not supported)


008 Create Observation Using Unsupported Profile
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-with-unsupported-profile.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status \n\n
    ...                 6. *VALIDATE* outcome against diagnostic text
	[Tags]             	invalid

	ehr.create new ehr    000_ehr_status.json
	observation.create observation    create-observation-with-unsupported-profile.json
    observation.validate response - 422 (profile not supported)


009 Create Coronavirus Lab Result
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-coronavirus-nachweis-test.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	coronavirus-lab-result    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create observation    create-coronavirus-nachweis-test.json
    observation.validate response - 201


010 Create Body Height
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-height.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-height    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create observation  create-body-height.json
	observation.validate response - 201


011 Create Pregnancy Status
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pregnancy-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	pregnancy-status    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create pregnancy status    create-pregnancy-status.json
  	observation.validate response - 201


012 Create Frailty Scale Score
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-clinical-frailty-scale-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	frailty-scale-score    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create frailty scale score    create-clinical-frailty-scale-score.json
  	observation.validate response - 201


013 Create Smoking Status
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-smoking-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	smoking-status    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create smoking status    create-smoking-status.json
  	observation.validate response - 201


014 Create Body Weight
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-weight.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-weight    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create body weight    create-body-weight.json
  	observation.validate response - 201


015 Create Patient in ICU
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-patient-in-icu.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	patient-in-icu    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create patient in icu    create-patient-in-icu.json
  	observation.validate response - 201


016 Create Blood Gas Panel
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-blood-gas-panel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	blood-gas-panel    valid   not-ready

	ehr.create new ehr    000_ehr_status.json
	observation.create blood gas panel    create-blood-gas-panel.json
	observation.validate response - 201


017 Create Oxygen Saturation in Arterial Blood
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-oxygen-saturation.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	oxygen-saturation   valid    not-ready

	ehr.create new ehr    000_ehr_status.json
	observation.create oxygen saturation    create-oxygen-saturation.json
  	observation.validate response - 201


018 Create History of Travel
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-history-of-travel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	history-of-travel   valid    not-ready

	ehr.create new ehr    000_ehr_status.json
	observation.create history of travel    create-history-of-travel.json
  	observation.validate response - 201


019 Create Sex assigned at Birth (Divers)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-sex-assigned-at-birth-divers.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	sex-assigned-at-birth   valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create sex assigned at birth    Sex assigned at Birth (Divers)    create-sex-assigned-at-birth-divers.json
    observation.validate response - 201


020 Create Sex assigned at Birth (Female)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-sex-assigned-at-birth-female.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	sex-assigned-at-birth   valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create sex assigned at birth    Sex assigned at Birth (Female)    create-sex-assigned-at-birth-female.json
    observation.validate response - 201


021 Create Sex assigned at Birth (Male)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-sex-assigned-at-birth-male.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	sex-assigned-at-birth   valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create sex assigned at birth    Sex assigned at Birth (Male)    create-sex-assigned-at-birth-male.json
    observation.validate response - 201


022 Create Sex assigned at Birth (Unspecified)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-sex-assigned-at-birth-unspecified.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	sex-assigned-at-birth   valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create sex assigned at birth    Sex assigned at Birth (Unspecified)    create-sex-assigned-at-birth-unspecified.json
    observation.validate response - 201


023 Create Sex assigned at Birth (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-sex-assigned-at-birth-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	sex-assigned-at-birth   valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create sex assigned at birth    Sex assigned at Birth (Unknown)    create-sex-assigned-at-birth-unknown.json
    observation.validate response - 201


024 Create Study Inclusion due to Covid-19 confirmed Diagnosis (Present)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-study-inclusion-due-to-covid-19-confirmed-diagnosis-present.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	study-inclusion-due-to-covid-19   valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create study inclusion due to covid 19    Study Inclusion due to Covid-19 confirmed Diagnosis (Present)    create-study-inclusion-due-to-covid-19-confirmed-diagnosis-present.json
    observation.validate response - 201


025 Create Study Inclusion due to Covid-19 confirmed Diagnosis (Absent)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-study-inclusion-due-to-covid-19-confirmed-diagnosis-absent.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	study-inclusion-due-to-covid-19   valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create study inclusion due to covid 19    Study Inclusion due to Covid-19 confirmed Diagnosis (Absent)    create-study-inclusion-due-to-covid-19-confirmed-diagnosis-absent.json
    observation.validate response - 201


026 Create Study Inclusion due to Covid-19 confirmed Diagnosis (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-study-inclusion-due-to-covid-19-confirmed-diagnosis-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	study-inclusion-due-to-covid-19   valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create study inclusion due to covid 19    Study Inclusion due to Covid-19 confirmed Diagnosis (Unknown)    create-study-inclusion-due-to-covid-19-confirmed-diagnosis-unknown.json
    observation.validate response - 201


027 Create Interventional Clinical Trial Participation (Present with EudraCT)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-interventional-clinical-trial-participation-present-with-EudraCT.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	interventional-clinical-trial-participation   valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create interventional clinical trial participation    Interventional Clinical Trial Participation (Present with EudraCT)    create-interventional-clinical-trial-participation-present-with-EudraCT.json
    observation.validate response - 201


028 Create Interventional Clinical Trial Participation (Present with NCT)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-interventional-clinical-trial-participation-present-with-NCT.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	interventional-clinical-trial-participation   valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create interventional clinical trial participation    Interventional Clinical Trial Participation (Present with NCT)    create-interventional-clinical-trial-participation-present-with-NCT.json
    observation.validate response - 201


029 Create Interventional Clinical Trial Participation (Present with EudraCT AND NCT)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-interventional-clinical-trial-participation-present-with-EudraCT-AND-NCT.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	interventional-clinical-trial-participation   valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create interventional clinical trial participation    Interventional Clinical Trial Participation (Present with EudraCT AND NCT)    create-interventional-clinical-trial-participation-present-with-EudraCT-AND-NCT.json
    observation.validate response - 201


030 Create Interventional Clinical Trial Participation (Absent)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-interventional-clinical-trial-participation-absent.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	interventional-clinical-trial-participation   valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create interventional clinical trial participation    Interventional Clinical Trial Participation (Absent)    create-interventional-clinical-trial-participation-absent.json
    observation.validate response - 201


031 Create Interventional Clinical Trial Participation (Not Applicable)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-interventional-clinical-trial-participation-not-applicable.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	interventional-clinical-trial-participation   valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create interventional clinical trial participation    Interventional Clinical Trial Participation (Not Applicable)    create-interventional-clinical-trial-participation-not-applicable.json
    observation.validate response - 201


032 Create Interventional Clinical Trial Participation (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-interventional-clinical-trial-participation-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	interventional-clinical-trial-participation   valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create interventional clinical trial participation    Interventional Clinical Trial Participation (Unknown)    create-interventional-clinical-trial-participation-unknown.json
    observation.validate response - 201


033 Create Known Exposure to Covid-19 (Present)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-known-exposure-to-covid-19-present.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	known-exposure-to-covid-19    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create known exposure to covid 19    Known Exposure to Covid-19 (Present)    create-known-exposure-to-covid-19-present.json
    observation.validate response - 201


034 Create Known Exposure to Covid-19 (Absent)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-known-exposure-to-covid-19-absent.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	known-exposure-to-covid-19    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create known exposure to covid 19    Known Exposure to Covid-19 (Absent)    create-known-exposure-to-covid-19-absent.json
    observation.validate response - 201


035 Create Known Exposure to Covid-19 (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-known-exposure-to-covid-19-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	known-exposure-to-covid-19    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create known exposure to covid 19    Known Exposure to Covid-19 (Unknown)    create-known-exposure-to-covid-19-unknown.json
    observation.validate response - 201


036 Create PaO2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-PaO2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	PaO2   valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create PaO2  PaO2  create-paO2.json
    observation.validate response - 201


037 Create PaCO2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-PaCO2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	PaCO2   valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create PaCO2  PaCO2  create-paco2.json
    observation.validate response - 201
