# Copyright (c) 2021 Renaud Subiger
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

Force Tags              documentreference_create    create



*** Variables ***




*** Test Cases ***
001 Create Consent
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _document-reference.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to patient endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             valid

	ehr.create new ehr    000_ehr_status.json
	documentreference.create DocumentReference  DocumentReference  document-reference.json
    documentreference.validate response - 201