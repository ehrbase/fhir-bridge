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



*** Settings ***

Library     REST    ssl_verify=false
Library     String
Library     Collections
Library     OperatingSystem
Library     Process
Library     JSONLibrary

Resource    ${EXECDIR}/robot/_resources/keywords/generic.robot
Resource    ${EXECDIR}/robot/_resources/keywords/ehr.robot
Resource    ${EXECDIR}/robot/_resources/keywords/condition.robot
Resource    ${EXECDIR}/robot/_resources/keywords/diagnostic.robot
Resource    ${EXECDIR}/robot/_resources/keywords/immunization.robot
Resource    ${EXECDIR}/robot/_resources/keywords/medicationstatement.robot
Resource    ${EXECDIR}/robot/_resources/keywords/observation.robot
Resource    ${EXECDIR}/robot/_resources/keywords/procedure.robot
Resource    ${EXECDIR}/robot/_resources/keywords/questionnaire.robot
Resource    ${EXECDIR}/robot/_resources/keywords/bundle.robot
Resource    ${EXECDIR}/robot/_resources/keywords/json-manipulation.robot
Resource    ${EXECDIR}/robot/_resources/keywords/patient.robot
Resource    ${EXECDIR}/robot/_resources/keywords/consent.robot
Resource    ${EXECDIR}/robot/_resources/keywords/documentreference.robot
Variables   ${EXECDIR}/robot/_resources/variables/sut_config.py
            ...    ${SUT}



*** Variables ***

# ${BASE_URL}                             http://localhost:8888/fhir-bridge/fhir
# ${EHRBASE_URL}                          http://localhost:8080/ehrbase/rest/openehr/v1
${DATA_SET_PATH_CONDITION}              ${EXECDIR}/../src/test/resources/Condition
${DATA_SET_PATH_DIAGNOSTIC}             ${EXECDIR}/../src/test/resources/DiagnosticReport
${DATA_SET_PATH_MEDICATIONSTATEMENT}    ${EXECDIR}/../src/test/resources/MedicationStatement
${DATA_SET_PATH_QUESTIONAIRE}           ${EXECDIR}/../src/test/resources/QuestionnaireResponse
${DATA_SET_PATH_OBSERVATION}            ${EXECDIR}/../src/test/resources/Observation
${DATA_SET_PATH_PROCEDURE}              ${EXECDIR}/../src/test/resources/Procedure
${DATA_SET_PATH_QUESTIONAIRE}           ${EXECDIR}/../src/test/resources/QuestionnaireResponse
${DATA_SET_PATH_BUNDLE}          	    ${EXECDIR}/../src/test/resources/Bundle
${DATA_SET_PATH_PATIENT}          	    ${EXECDIR}/../src/test/resources/Patient
${DATA_SET_PATH_CONSENT}          	    ${EXECDIR}/../src/test/resources/Consent
${DATA_SET_PATH_DOCUMENTREFERENCE}	    ${EXECDIR}/../src/test/resources/DocumentReference
${VALID EHR DATA SETS}                  ${EXECDIR}/robot/_resources/test_data/ehr/valid
${OUTPUT_LEVEL}                         verbose

${SUT}                                  LOCAL
${AUTH_TYPE}                            BASIC
${NODOCKER}                             False
${CODE_COVERAGE}                        False
${REDUMP_REQUIRED}                      ${FALSE}
${ALLOW-TEMPLATE-OVERWRITE}             ${TRUE}
${CACHE-ENABLED}                        ${TRUE}
