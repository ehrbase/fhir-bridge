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

Force Tags              condition_search



*** Variables ***



*** Test Cases ***
001 Search Diagnose Condition
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-condition-default.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *GET* ``${BASE_URL}/Condition?subject.identifier=${subject_id}`` \n\n
    ...                 6. *VALIDATE* response status against 200
    [Tags]              diagnose-condition    valid

    condition.create diagnose condition    create-condition-default.json
    condition.get diagnose condition



*** Keywords ***
establish preconditions
    generic.prepare new request session     Prefer=return=representation
    ...									    Authorization=${AUTHORIZATION['Authorization']}
    ehr.create new ehr    000_ehr_status.json
