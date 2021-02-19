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
...                                                            Authorization=${AUTHORIZATION['Authorization']}
# ...															   Authorization=${AUTHORIZATION['Authorization']}

Force Tags              diagnostic-report_create    create



*** Variables ***




*** Test Cases ***
001 Create Diagnostic Report
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-diagnosticReport.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to diagnostic endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              diagnostic-report    valid   xxx

    Log Many    ${AUTHORIZATION}
    ehr.create new ehr                      000_ehr_status.json
    diagnostic.create diagnostic report     create-diagnosticReport.json 
    diagnostic.validate response - 201


002 Create Diagnostic Report w/o Observation
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-diagnosticReport-without-observation.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to diagnostic endpoint\n\n
	...                 5. *VALIDATE* the response status \n\n
    ...                 6. *VALIDATE* outcome against diagnostic text
    [Tags]              invalid

    ehr.create new ehr                      000_ehr_status.json
    diagnostic.create diagnostic report     create-diagnosticReport-without-observation.json 
    diagnostic.validate response - 422 (missing observation)


003 Create Diagnostic Report Using Default Profile
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-diagnosticReport-with-default-profile.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to diagnostic endpoint\n\n
	...                 5. *VALIDATE* the response status \n\n
    ...                 6. *VALIDATE* outcome against diagnostic text
    [Tags]              invalid

    ehr.create new ehr                      000_ehr_status.json
    diagnostic.create diagnostic report     create-diagnosticReport-with-default-profile.json 
    diagnostic.validate response - 422 (profile not supported)


004 Create Diagnostic Report Using Unsupported Profile
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-diagnosticReport-hls-genetics-result.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to diagnostic endpoint\n\n
	...                 5. *VALIDATE* the response status \n\n
    ...                 6. *VALIDATE* outcome against diagnostic text
    [Tags]              invalid

    ehr.create new ehr                      000_ehr_status.json
    diagnostic.create diagnostic report     create-diagnosticReport-hls-genetics-result.json 
    diagnostic.validate response - 422 (profile not supported)


005 Create Diagnostic Report Radiology - Typical Finding
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-diagnostic-report-radiology-typical-finding.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to diagnostic endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              radiology    valid   not-ready    not-implemented

    ehr.create new ehr                      000_ehr_status.json
    diagnostic.create diagnostic report radiology       Diagnostic Report Radiology - Typical Finding    create-diagnostic-report-radiology-typical-finding.json 
    diagnostic.validate response - 201


006 Create Diagnostic Report Radiology - Unspecific Finding
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-diagnostic-report-radiology-unspecific-finding.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to diagnostic endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              radiology    valid   not-ready    not-implemented

    ehr.create new ehr                      000_ehr_status.json
    diagnostic.create diagnostic report radiology       Diagnostic Report Radiology - Unspecific Finding    create-diagnostic-report-radiology-unspecific-finding.json 
    diagnostic.validate response - 201


007 Create Diagnostic Report Radiology - Normal Findings
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-diagnostic-report-radiology-normal-finding.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to diagnostic endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              radiology    valid   not-ready    not-implemented

    ehr.create new ehr                      000_ehr_status.json
    diagnostic.create diagnostic report radiology       Diagnostic Report Radiology - Normal Findings    create-diagnostic-report-radiology-normal-finding.json 
    diagnostic.validate response - 201
