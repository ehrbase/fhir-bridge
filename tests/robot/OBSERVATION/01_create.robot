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
	[Tags]             body-temperature    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create body temperature    create-body-temp.json
    observation.validate response - 201


002 Create Blood Pressure 
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-blood-pressure.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             blood-pressure    valid

	ehr.create new ehr    					000_ehr_status.json
	observation.create blood pressure    	create-blood-pressure.json
    observation.validate response - 201


003 Create FIO2 
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-fio2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             fio2    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create FIO2    create-fio2.json
    observation.validate response - 201


004 Create Heart Rate 
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-heart-rate.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             heart-rate    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create heart rate    create-heart-rate.json
    observation.validate response - 201


005 Create Sofa Score
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             sofa-score    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create sofa score    create-sofa-score.json
    observation.validate response - 201


006 Create Observation Lab
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             observation-lab    valid

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
	[Tags]             invalid

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
	[Tags]             invalid

	ehr.create new ehr    000_ehr_status.json
	observation.create observation    create-observation-with-unsupported-profile.json
    observation.validate response - 422 (profile not supported)


009 Create Coronavirus Lab Result
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-coronavirus-nachweis-test.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             coronavirus-lab-result    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create observation    create-coronavirus-nachweis-test.json
    observation.validate response - 201


010 Create Body Height
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-height.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             body-height    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create observation  create-body-height.json
	observation.validate response - 201


011 Create Pregnancy Status
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pregnancy-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             pregnancy-status    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create pregnancy status    create-pregnancy-status.json
  	observation.validate response - 201


012 Create Frailty Scale Score
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-clinical-frailty-scale-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             frailty-scale-score    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create frailty scale score    create-clinical-frailty-scale-score.json
  	observation.validate response - 201


013 Create Smoking Status
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-smoking-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             smoking-status    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create smoking status    create-smoking-status.json
  	observation.validate response - 201


014 Create Body Weight
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-weight.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             body-weight    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create body weight    create-body-weight.json
  	observation.validate response - 201


015 Create Patient in ICU
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-patient-in-icu.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             patient-in-icu    valid

	ehr.create new ehr    000_ehr_status.json
	observation.create patient in icu    create-patient-in-icu.json
  	observation.validate response - 201


016 Create Blood Gas Panel
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-blood-gas-panel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             blood-gas-panel    valid   not-ready

	ehr.create new ehr    000_ehr_status.json
	observation.create blood gas panel    create-blood-gas-panel.json
	observation.validate response - 201


017 Create Oxygen Saturation in Arterial Blood
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-oxygen-saturation.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             oxygen-saturation   valid    not-ready

	ehr.create new ehr    000_ehr_status.json
	observation.create oxygen saturation    create-oxygen-saturation.json
  	observation.validate response - 201


018 Create History of Travel
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-history-of-travel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             history-of-travel   valid    not-ready

	ehr.create new ehr    000_ehr_status.json
	observation.create history of travel    create-history-of-travel.json
  	observation.validate response - 201
