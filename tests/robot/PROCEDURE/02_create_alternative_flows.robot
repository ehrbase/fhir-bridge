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

Force Tags              procedure_create



*** Variables ***




*** Test Cases ***

001 Create Radiology Procedures - Chest X-Ray Alternative dcm datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-radiology-procedures-chest-x-ray_dcm_datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	radiology-procedures    chest-x-ray    valid    not-ready    not-ready_not-implemented    alternative    dcm    datetime

    ehr.create new ehr    000_ehr_status.json
    procedure.create radiology procedures    Radiology Procedures - Chest X-Ray dcm datetime    create-radiology-procedures-chest-x-ray_dcm_datetime.json
    procedure.validate response - 201


002 Create Radiology Procedures - Chest X-Ray Alternative dcm period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-radiology-procedures-chest-x-ray_dcm_period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	radiology-procedures    chest-x-ray    valid    not-ready    not-ready_not-implemented   alternative    dcm    period

    ehr.create new ehr    000_ehr_status.json
    procedure.create radiology procedures    Radiology Procedures - Chest X-Ray dcm period    create-radiology-procedures-chest-x-ray_dcm_period.json
    procedure.validate response - 201



003 Create Radiology Procedures - Chest X-Ray Alternative dcm period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-radiology-procedures-chest-x-ray_dcm_period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	radiology-procedures    chest-x-ray    valid    not-ready    not-ready_not-implemented   alternative    dcm    period_2

    ehr.create new ehr    000_ehr_status.json
    procedure.create radiology procedures    Radiology Procedures - Chest X-Ray dcm period_2   create-radiology-procedures-chest-x-ray_dcm_period_2.json
    procedure.validate response - 201



004 Create Radiology Procedures - Chest X-Ray Alternative sct datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-radiology-procedures-chest-x-ray_sct_datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	radiology-procedures    chest-x-ray    valid    not-ready    not-ready_not-implemented   alternative    sct    datetime

    ehr.create new ehr    000_ehr_status.json
    procedure.create radiology procedures    Radiology Procedures - Chest X-Ray sct datetime    create-radiology-procedures-chest-x-ray_sct_datetime.json
    procedure.validate response - 201



005 Create Radiology Procedures - Chest X-Ray sct period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-radiology-procedures-chest-x-ray_sct_period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	radiology-procedures    chest-x-ray    valid    not-ready    not-ready_not-implemented   alternative    sct    period

    ehr.create new ehr    000_ehr_status.json
    procedure.create radiology procedures    Radiology Procedures - Chest X-Ray sct period    create-radiology-procedures-chest-x-ray_sct_period.json
    procedure.validate response - 201



006 Create Radiology Procedures - Chest X-Ray sct period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-radiology-procedures-chest-x-ray_sct_period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	radiology-procedures    chest-x-ray    valid    not-ready    not-ready_not-implemented   alternative    sct    period_2

    ehr.create new ehr    000_ehr_status.json
    procedure.create radiology procedures    Radiology Procedures - Chest X-Ray sct period_2    create-radiology-procedures-chest-x-ray_sct_period_2.json
    procedure.validate response - 201