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

Test Setup              establish preconditions

Force Tags              observation_search


*** Variables ***




*** Test Cases ***
001 Search Body Temperature
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-temp.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *GET* ``${BASE_URL}/Observation?subject.identifier=${subject_id}`` \n\n
    ...                 6. *VALIDATE* response status against 200
    [Tags]              body-temperature    valid    not-ready    not-implemented

    observation.create body temperature  Body Temperature  create-body-temp.json
	observation.get body temperature


002 Search Observation Lab
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-observation-lab.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *GET* ``${BASE_URL}/Observation?subject.identifier=${subject_id}`` \n\n
    ...                 6. *VALIDATE* response status against 200
    [Tags]              observation-lab    valid    not-ready    not-implemented

    observation.create observation lab    create-observation-lab.json
	observation.get observation lab


003 Search Coronavirus Lab Results
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-coronavirus-nachweis-test.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *GET* ``${BASE_URL}/Observation?subject.identifier=${subject_id}`` \n\n
    ...                 6. *VALIDATE* response status against 200
    [Tags]              coronavirus-lab-result    valid    not-ready    not-implemented

    observation.create coronavirus lab result    create-coronavirus-nachweis-test.json
	observation.get coronavirus lab results


004 Search Heart Rate Results
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-heart-rate.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *GET* ``POST {{ehrbase_url}}/query/aql WITH "q": "SELECT c FROM EHR e [ehr_id/value='{{ehr_id}}'] CONTAINS COMPOSITION c" `` \n\n
    ...                 6. *VALIDATE* response status against 200
    [Tags]              heart-rate    valid    not-ready    not-implemented

    observation.create heart rate  Heart Rate  create-heart-rate.json
	extract identifier_value from response
	observation.get heart rate results


*** Keywords ***
establish preconditions
    generic.prepare new request session    Prefer=return=representation
    ...									   Authorization=${AUTHORIZATION['Authorization']}
    ehr.create new ehr    000_ehr_status.json
