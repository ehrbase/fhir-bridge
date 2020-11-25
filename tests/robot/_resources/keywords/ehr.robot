# Copyright (c) 2019 Wladislaw Wagner (Vitasystems GmbH), P. Wohlfarth (Appsfactory)
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



*** Keywords ***
create new ehr
    [Arguments]         ${ehr_status_object}
    [Documentation]     Creates new EHR record with a server-generated ehr_id.
    ...                 DEPENDENCY: `prepare new request session`
    ...                 :ehr_status_object: ehr_status_as_json_file

    ${ehr_status_json}  Load JSON From File    ${VALID EHR DATA SETS}/${ehr_status_object}
                        Update Value To Json    ${ehr_status_json}    $.subject.external_ref.id.value
                        ...    ${{str(uuid.uuid4())}}
                        Update Value To Json    ${ehr_status_json}    $.subject.external_ref.namespace
                        ...    namespace_${{''.join(random.choices(string.digits, k=7))}}

    &{resp}=            POST    ${EHRBASE_URL}/ehr    ${ehr_status_json}
                        Integer      response status    201
                        Output Debug Info To Console

                        Set Suite Variable    ${response}    ${resp}
                        extract subject_id from response


create ehr
    [Documentation]     Creates new EHR record with a server-generated ehr_id.
    ...                 DEPENDENCY: `prepare new request session`

    &{resp}=            REST.POST    ${EHRBASE_URL}/ehr
                        Set Test Variable    ${response}    ${resp}
                        Output Debug Info To Console
                        extract ehr_id from response
