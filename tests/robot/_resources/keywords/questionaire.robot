# Copyright (c) 2019 Wladislaw Wagner (Vitasystems GmbH), Peter Wohlfarth (Appsfactory GmbH), Dave Petzold (Appsfactory GmbH)
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
create questionnaire response
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_QUESTIONAIRE}/${fhir_resource}
                        # Output    ${payload}

                        # comment: Questionaire resource does not have a Patient/EHR reference yet
                        # Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/QuestionnaireResponse    body=${payload}
                        Output Debug Info To Console


validate response - 201
    [Documentation]     Validates response of POST to /QuestionnaireResponse endpoint
                        Integer    response status    201
