# Copyright (c) 2020 Wladislaw Wagner (Vitasystems GmbH)
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



# *** Settings ***



*** Keywords ***
prepare new request session
    [Arguments]         ${headers}=JSON    &{extra_headers}
    [Documentation]     Prepares request settings for RESTistance AND RequestsLibrary
    ...                 :headers: valid argument values:
    ...                     - JSON (default)
    ...                     - XML
    ...                     - no accept header
    ...                     - no content header
    ...                     - no accept header xml
    ...                     - no accept/content headers
    ...                     - no headers
    ...
    ...                 :extra_headers: optional, valid value examples: 
    ...                     - Prefer=return=representation
    ...                     - If-Match={ehrstatus_uid}
    
                        Log Many            ${headers}  ${extra_headers}

                        # case: JSON (DEFAULT)
                        Run Keyword If      $headers=='JSON'    set request headers
                        ...                 content=application/json
                        ...                 accept=application/json
                        ...                 &{extra_headers}

                        # case: no Accept header, Content-Type=JSON
                        Run Keyword If      $headers=='no accept header'    set request headers
                        ...                 content=application/json
                        ...                 &{extra_headers}

                        # case: no Content-Type header
                        Run Keyword If      $headers=='no content header'    set request headers
                        ...                 accept=application/json
                        ...                 &{extra_headers}

                        # case: no Accept & no Content-Type header
                        Run Keyword If      $headers=='no accept/content headers'    set request headers
                        ...                 &{extra_headers}

                        # case: no headers
                        Run Keyword If      $headers=='no headers'    set request headers  


set request headers
    [Arguments]         ${content}=${NONE}  ${accept}=${NONE}  &{extra_headers}
    [Documentation]     Sets the headers of a request
    ...                 :content: None (default) / application/json / application/xml
    ...                 :accept: None (default) / application/json / application/xml
    ...                 :extra_headers: optional - e.g. Prefer=return=representation
    ...                                            e.g. If-Match={ehrstatus_uid}
    ...
    ...                 ATTENTIION: RESTInstance lib sets {"Content-Type": "applicable/json"}
    ...                             and {"Accept": "application/json, */*"} by default!
    ...                             As a workaround set them to None, null or empty - i.e.:
    ...                             - "Content-Type=    "
    ...                             - "Accept=    "

                        Log Many            ${content}  ${accept}  ${extra_headers}
                        Run Keyword If    "${content}"=="${NONE}" and "${accept}"=="${NONE}"
                        ...    Log To Console   \nWARNING: NO REQUEST HEADERS SET!

    &{headers}=         Create Dictionary     &{EMPTY}
    
                        Set To Dictionary    ${headers}
                        ...                  Content-Type=${content}
                        ...                  Accept=${accept}
                        ...                  &{extra_headers}

    # comment: headers for RESTinstance Library
    &{headers}=         Set Headers         ${headers}
                        # Set Headers         ${authorization}


extract subject_id from response
    [Documentation]     Extracts subject_id from response of preceding request.
    ...                 This KW executes only in EHR_SERVICE test suite, it is ignored
    ...                 in all over test suites.

    ${subjectid}=       String      response body ehr_status subject external_ref id value
                        Log To Console    \n\tDEBUG OUTPUT - EHR_STATUS SUBJECT_ID: \n\t${subjectid}[0]
                        Set Suite Variable    ${subject_id}    ${subjectid}[0]
    

Output Debug Info To Console
    [Documentation]     Prints all details of a request to console in JSON style.
    ...                 Can also be used to output a single JSON
    ...                 - request headers
    ...                 - request body
    ...                 - response headers
    ...                 - response body
    [Arguments]         ${payload}=${None}
    Run Keyword And Return If   "${OUTPUT_LEVEL}"=="verbose" and ${payload}!=${None}
                                ...    Output    ${payload}
    Run Keyword If              "${OUTPUT_LEVEL}"=="verbose"    Output


TRACE GITHUB ISSUE
    [Arguments]     ${GITHUB_ISSUE}
    ...             ${not-ready}=
    ...             ${message}=Next step fails due to a bug!
    ...             ${loglevel}=ERROR

                                                        # TODO: RENAME TO   fhir-bridge when migration finished
                    Log    ${message} | <a href="https://github.com/ehrbase/fhir-bridge-poc/issues/${GITHUB_ISSUE}">Github ISSUE #${GITHUB_ISSUE}</a>
                    ...    level=${loglevel}    html=True

                    Set Tags    bug    GITHUB ISSUE ${GITHUB_ISSUE}
                    Run Keyword If    '${not-ready}'=='not-ready'    Set Tags    not-ready
