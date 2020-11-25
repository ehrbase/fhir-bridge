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

Force Tags              create



*** Variables ***




*** Test Cases ***
001 Create Diagnostic Report
    [Documentation]     1. create EHR
    ...                 2. trigger diagnosticreport endpoint

    ehr.create new ehr    000_ehr_status.json
    diagnostic.create diagnostic report    diagnosticreport-diagnosticreportlab-example-contained_obs.json 
    diagnostic.validate response - 201


002 Create Diagnostic Report w/o Observation
    [Documentation]     Trigger endpoint using invalid payload.

    ehr.create new ehr    000_ehr_status.json
    diagnostic.create diagnostic report    diagnosticreport-diagnosticreportlab-example.json 
    diagnostic.validate response - 422 (missing observation)


003 Create Diagnostic Report Using Default Profile
    [Documentation]     Trigger endpoint using invalid payload.

    ehr.create new ehr    000_ehr_status.json
    diagnostic.create diagnostic report    diagnosticreport-example.json 
    diagnostic.validate response - 422 (profile not supported)


004 Create Diagnostic Report Using Unsupported Profile
    [Documentation]     Trigger endpoint using invalid payload.

    ehr.create new ehr    000_ehr_status.json
    diagnostic.create diagnostic report    diagnosticreport-hla-genetics-results-example.json 
    diagnostic.validate response - 422 (profile not supported)
