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
001 Create Diagnostic Report
    [Documentation]     1. create EHR
    ...                 2. trigger diagnosticreport endpoint
    [Tags]              diagnostic-report    valid

    ehr.create new ehr    000_ehr_status.json
    diagnostic.create diagnostic report    create.json 
    diagnostic.validate response - 201


002 Create Diagnostic Report w/o Observation
    [Documentation]     Trigger endpoint using invalid payload.
    [Tags]              invalidvalid

    ehr.create new ehr    000_ehr_status.json
    diagnostic.create diagnostic report    create-without-observation.json 
    diagnostic.validate response - 422 (missing observation)


003 Create Diagnostic Report Using Default Profile
    [Documentation]     Trigger endpoint using invalid payload.
    [Tags]              invalid

    ehr.create new ehr    000_ehr_status.json
    diagnostic.create diagnostic report    create-with-default-profile.json 
    diagnostic.validate response - 422 (profile not supported)


004 Create Diagnostic Report Using Unsupported Profile
    [Documentation]     Trigger endpoint using invalid payload.
    [Tags]              invalid

    ehr.create new ehr    000_ehr_status.json
    diagnostic.create diagnostic report    create-hls-genetics-result.json 
    diagnostic.validate response - 422 (profile not supported)
