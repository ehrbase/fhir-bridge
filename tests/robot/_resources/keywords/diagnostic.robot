# Copyright (c) 2020 Wladislaw Wagner (Vitasystems GmbH), Peter Wohlfarth (Appsfactory GmbH), Dave Petzold (Appsfactory GmbH)
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
create diagnostic report
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_DIAGNOSTIC}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/DiagnosticReport    body=${payload}
                        Output Debug Info To Console


validate response - 201
    Integer    response status    201

    String     response body resourceType    DiagnosticReport
    String     response body id
    String     response body meta versionId    1
    string     response body contained 0 resourceType    Observation


validate response - 422 (missing observation)
    Integer    response status    422

    # String     response body issue 0 diagnostics
    String     $.issue[0].diagnostics    # same as line above but in JSONPath syntax
    ...        pattern=There was a problem saving the compositionOne contained Observation was expected 0 were received in DiagnosticReport
    
    
validate response - 422 (profile not supported)
    Integer    response status    422

    String     $.issue[0].diagnostics 
    ...        pattern=is not supported for DiagnosticReport. One of the following profiles is expected
