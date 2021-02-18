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

Force Tags              condition_create    create



*** Variables ***




*** Test Cases ***


001 Create Condition Symptoms-Covid-19 sct
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-symptoms-covid-19-present_sct.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	symptoms-covid-19    valid    alternative    sct    not-ready    not-ready_bug

    ehr.create new ehr    000_ehr_status.json
    condition.create symptoms-covid-19    Symptoms-Covid-19 sct    create-symptoms-covid-19-present_sct.json
    condition.validate response - 201



002 Create Condition Symptoms-Covid-19 icd10
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-symptoms-covid-19-present_icd10.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	symptoms-covid-19    valid    alternative    icd10    not-ready    not-ready_bug

    ehr.create new ehr    000_ehr_status.json
    condition.create symptoms-covid-19    Symptoms-Covid-19 icd10   create-symptoms-covid-19-present_icd10.json
    condition.validate response - 201



003 Create Condition Symptoms-Covid-19 alphaid
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-symptoms-covid-19-present_alphaid.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	symptoms-covid-19    valid    alternative    alphaid    not-ready    not-ready_bug

    ehr.create new ehr    000_ehr_status.json
    condition.create symptoms-covid-19    Symptoms-Covid-19 alphaid    create-symptoms-covid-19-present_alphaid.json
    condition.validate response - 201