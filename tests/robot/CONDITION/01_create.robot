# Copyright (c) 2020 P. Wohlfarth (Appsfactory), Wladislaw Wagner (Vitasystems GmbH)
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
001 Create Diagnose Condition
    [Documentation]     1. create EHR
    ...                 2. trigger condition endpoint
    [Tags]              diagnose-condition    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create diagnose condition    create-default.json
    condition.validate response - 201


002 Create Condition Using Invalid Profile
    [Documentation]     1. create EHR
    ...                 2. trigger condition endpoint using invalid payload
    [Tags]              invalid

    ehr.create new ehr    000_ehr_status.json
    condition.create diagnose condition    create-with-invalid-profile.json
    condition.validate response - 422 (Unprocessable Entity)
