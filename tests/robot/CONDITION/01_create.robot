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

Test Setup              generic.prepare new request session    Prefer=return=representation
...															   Authorization=${AUTHORIZATION['Authorization']}

Force Tags              condition_create    create



*** Variables ***




*** Test Cases ***
001 Create Diagnose Condition
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-condition-default.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              diagnose-condition    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create diagnose condition    create-condition-default.json
    condition.validate response - 201


002 Create Condition Using Invalid Profile
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-condition-with-invalid-profile.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status \n\n
    ...                 6. *VALIDATE* outcome against diagnostic text
    [Tags]              invalid

    ehr.create new ehr    000_ehr_status.json
    condition.create diagnose condition    create-condition-with-invalid-profile.json
    condition.validate response - 422 (Unprocessable Entity)


003 Create Condition Symptoms-Covid-19 (Present)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-symptoms-covid-19-present.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              symptoms-covid-19    valid    247

    ehr.create new ehr    000_ehr_status.json
    condition.create symptoms-covid-19    Symptoms-Covid-19    Symptom/create-symptoms-covid-19-present.json
    condition.validate response - 201
    [Teardown]          TRACE GITHUB ISSUE  247  bug


004 Create Condition Symptoms-Covid-19 (Absent)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-symptoms-covid-19-absent.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              symptoms-covid-19    valid    247

    ehr.create new ehr    000_ehr_status.json
    condition.create symptoms-covid-19    Symptoms-Covid-19    Symptom/create-symptoms-covid-19-absent.json
    condition.validate response - 201
    [Teardown]          TRACE GITHUB ISSUE  247  bug


005 Create Condition Symptoms-Covid-19 (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-symptoms-covid-19-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              symptoms-covid-19    valid    247

    ehr.create new ehr    000_ehr_status.json
    condition.create symptoms-covid-19    Symptoms-Covid-19    Symptom/create-symptoms-covid-19-unknown.json
    condition.validate response - 201
    [Teardown]          TRACE GITHUB ISSUE  247  bug


006 Create Condition Diabetes Mellitus
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-diabetes-mellitus.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              diabetes-mellitus    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create diabetes mellitus    Diabetes Mellitus    create-diabetes-mellitus.json
    condition.validate response - 201


007 Create Condition Diabetes Mellitus Type 1
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-diabetes-mellitus-type-1.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              diabetes-mellitus    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create diabetes mellitus    Diabetes Mellitus Type 1    create-diabetes-mellitus-type-1.json
    condition.validate response - 201


008 Create Condition Diabetes Mellitus Type 2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-diabetes-mellitus-type-2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              diabetes-mellitus    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create diabetes mellitus    Diabetes Mellitus Type 2    create-diabetes-mellitus-type-2.json
    condition.validate response - 201


009 Create Condition Diabetes Mellitus Type 2 Insulin Treated
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-diabetes-mellitus-type-2-insulin-treated.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              diabetes-mellitus    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create diabetes mellitus    Diabetes Mellitus Type 2 Insulin Treated    create-diabetes-mellitus-type-2-insulin-treated.json
    condition.validate response - 201


010 Create Rheumatological Immunological Diseases (Rheumatism)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-rheumatological-immunological-diseases-rheumatism.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              diabetes-mellitus    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create rheumatological immunological diseases    Rheumatological Immunological Diseases (Rheumatism)    create-rheumatological-immunological-diseases-rheumatism.json
    condition.validate response - 201


011 Create Rheumatological Immunological Diseases (Rheumatoid Arthritis)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-rheumatological-immunological-diseases-rheumatoid-arthritis.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              diabetes-mellitus    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create rheumatological immunological diseases    Rheumatological Immunological Diseases (Rheumatoid Arthritis)    create-rheumatological-immunological-diseases-rheumatoid-arthritis.json
    condition.validate response - 201


012 Create Chronic Lung Diseases
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-lung-disease.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-lung-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic lung disease    create-chronic-lung-disease.json
    condition.validate response - 201


013 Create Chronic Obstructive Lung Disease
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-obstructive-lung-disease.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-lung-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic obstructive lung disease    create-chronic-obstructive-lung-disease.json
    condition.validate response - 201


014 Create Chronic Lung Disease (Fibrosis of Lung)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-lung-disease-fibrosis-of-lung.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-lung-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Fibrosis of Lung)    create-chronic-lung-disease-fibrosis-of-lung.json
    condition.validate response - 201


015 Create Chronic Lung Disease (Asthma)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-lung-disease-asthma.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-lung-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Asthma)    create-chronic-lung-disease-asthma.json
    condition.validate response - 201


016 Create Chronic Lung Disease (Cystic Fibrosis)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-lung-disease-cystic-fibrosis.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-lung-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Cystic Fibrosis)    create-chronic-lung-disease-cystic-fibrosis.json
    condition.validate response - 201


017 Create Chronic Lung Disease (Extreme Obesity with Alveolar Hypoventilation)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-lung-disease-with-alveolar-hypoventilation.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-lung-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Extreme Obesity with Alveolar Hypoventilation)    create-chronic-lung-disease-with-alveolar-hypoventilation.json
    condition.validate response - 201


018 Create Chronic Lung Disease (Pulmonary hypertension)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-lung-disease-pulmonary-hypertension.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-lung-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Pulmonary hypertension)    create-chronic-lung-disease-pulmonary-hypertension.json
    condition.validate response - 201


019 Create Chronic Lung Disease (Sleep Apnea)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-lung-disease-sleep-apnea.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-lung-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Sleep Apnea)    create-chronic-lung-disease-sleep-apnea.json
    condition.validate response - 201


020 Create Chronic Lung Disease (Obstructive Sleep Apnea Syndrome)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-lung-disease-obstructive-sleep-apnea.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-lung-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Obstructive Sleep Apnea Syndrome)    create-chronic-lung-disease-obstructive-sleep-apnea.json
    condition.validate response - 201


021 Create Chronic Liver Disease
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-liver-disease.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-liver-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic liver diseases    Chronic Liver Disease    create-chronic-liver-disease.json
    condition.validate response - 201


022 Create Chronic Liver Disease (Autoimmune Liver Disease)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-liver-disease-autoimmune.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-liver-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic liver diseases    Chronic Liver Disease (Autoimmune Liver Disease)    create-chronic-liver-disease-autoimmune.json
    condition.validate response - 201


023 Create Chronic Liver Disease (Chronic Viral Hepatitis)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-liver-disease-chronic-viral-hepatitis.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-liver-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic liver diseases    Chronic Liver Disease (Chronic Viral Hepatitis)    create-chronic-liver-disease-chronic-viral-hepatitis.json
    condition.validate response - 201


024 Create Chronic Liver Disease (Cirrhosis of Liver)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-liver-disease-cirrhosis-of-liver.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-liver-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic liver diseases    Chronic Liver Disease (Cirrhosis of Liver)    create-chronic-liver-disease-cirrhosis-of-liver.json
    condition.validate response - 201


025 Create Chronic Liver Disease (Steatosis of Liver)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-liver-disease-steatosis-of-liver.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-liver-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic liver diseases    Chronic Liver Disease (Steatosis of Liver)    create-chronic-liver-disease-steatosis-of-liver.json
    condition.validate response - 201


026 Create Malignant Neoplastic Disease (Absent)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-malignant-neoplastic-disease-absent.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              malignant-neoplastic-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create malignant neoplastic disease    create-malignant-neoplastic-disease-absent.json
    condition.validate response - 201


027 Create Malignant Neoplastic Disease (Present Active)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-malignant-neoplastic-disease-present-active.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              malignant-neoplastic-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create malignant neoplastic disease    create-malignant-neoplastic-disease-present-active.json
    condition.validate response - 201


028 Create Malignant Neoplastic Disease (Present Remission)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-malignant-neoplastic-disease-present-remission.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              malignant-neoplastic-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create malignant neoplastic disease    create-malignant-neoplastic-disease-present-remission.json
    condition.validate response - 201


029 Create Malignant Neoplastic Disease (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-malignant-neoplastic-disease-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              malignant-neoplastic-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create malignant neoplastic disease    create-malignant-neoplastic-disease-unknown.json
    condition.validate response - 201


030 Create Diagnosis Covid-19 (Absent)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-diagnosis-covid-19-absent.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              diagnosis-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create diagnosis covid-19    create-diagnosis-covid-19-absent.json
    condition.validate response - 201


031 Create Diagnosis Covid-19 (Present Active)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-diagnosis-covid-19-present-active.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              diagnosis-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create diagnosis covid-19    create-diagnosis-covid-19-present-active.json
    condition.validate response - 201


032 Create Diagnosis Covid-19 (Present Remission)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-diagnosis-covid-19-present-remission.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              diagnosis-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create diagnosis covid-19    create-diagnosis-covid-19-present-remission.json
    condition.validate response - 201

# TODO: CLARIFY IF UNKNOW STATUS FOR DIAGNOSIS MAKES SENSE
033 Create Diagnosis Covid-19 (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-diagnosis-covid-19-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              diagnosis-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create diagnosis covid-19    create-diagnosis-covid-19-unknown.json
    condition.validate response - 201


034 Create Chronic Kidney Disease (Disorder - Present)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-kidney-disease-present.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-kidney-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic kidney diseases    Chronic Kidney Disease (Disorder - Present)    create-chronic-kidney-disease-present.json
    condition.validate response - 201


035 Create Chronic Kidney Disease (Disorder - Absent)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-kidney-disease-absent.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-kidney-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic kidney diseases    Chronic Kidney Disease (Disorder - Absent)    create-chronic-kidney-disease-absent.json
    condition.validate response - 201


036 Create Chronic Kidney Disease (Disorder - Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-kidney-disease-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-kidney-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic kidney diseases    Chronic Kidney Disease (Disorder - Unknown)    create-chronic-kidney-disease-unknown.json
    condition.validate response - 201
    [Teardown]  TRACE GITHUB ISSUE    288

037 Create Chronic Kidney Disease (Disorder - stage 5 on dialysis)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-kidney-disease-stage-5-dialysis.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-kidney-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic kidney diseases    Chronic Kidney Disease (Disorder - stage 5 on dialysis)    create-chronic-kidney-disease-stage-5-dialysis.json
    condition.validate response - 201


038 Create Chronic Neurological or Mental Diseases (Anxiety Disorder)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-neurological-or-mental-disease-anxiety.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-neurological-or-mental-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Anxiety Disorder)    create-chronic-neurological-or-mental-disease-anxiety.json
    condition.validate response - 201


039 Create Chronic Neurological or Mental Diseases (Chronic Nervous System Disorder)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-neurological-or-mental-disease-chronic-nervous-system.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-neurological-or-mental-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Chronic Nervous System Disorder)    create-chronic-neurological-or-mental-disease-chronic-nervous-system.json
    condition.validate response - 201


040 Create Chronic Neurological or Mental Diseases (Combined disorder of muscle AND peripheral nerve)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-neurological-or-mental-disease-muscle-and-peripheral-nerve.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-neurological-or-mental-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Combined disorder of muscle AND peripheral nerve)    create-chronic-neurological-or-mental-disease-muscle-and-peripheral-nerve.json
    condition.validate response - 201
    [Teardown]  TRACE GITHUB ISSUE    288 

041 Create Chronic Neurological or Mental Diseases (Dementia)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-neurological-or-mental-disease-dementia.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-neurological-or-mental-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Dementia)    create-chronic-neurological-or-mental-disease-dementia.json
    condition.validate response - 201
    [Teardown]  TRACE GITHUB ISSUE    288  

042 Create Chronic Neurological or Mental Diseases (Depressive Disorder)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-neurological-or-mental-disease-depressive.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-neurological-or-mental-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Depressive Disorder)    create-chronic-neurological-or-mental-disease-depressive.json
    condition.validate response - 201


043 Create Chronic Neurological or Mental Diseases (Epilepsy)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-neurological-or-mental-disease-epilepsy.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-neurological-or-mental-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Epilepsy)    create-chronic-neurological-or-mental-disease-epilepsy.json
    condition.validate response - 201
    [Teardown]  TRACE GITHUB ISSUE    288  

044 Create Chronic Neurological or Mental Diseases (History of Cerebrovascular Accident with Residual Deficits)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-neurological-or-mental-disease-history-of-cerebrovascular-accident-with.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-neurological-or-mental-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (History of Cerebrovascular Accident with Residual Deficits)    create-chronic-neurological-or-mental-disease-history-of-cerebrovascular-accident-with.json
    condition.validate response - 201


045 Create Chronic Neurological or Mental Diseases (History of Cerebrovascular Accident without Residual Deficits)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-neurological-or-mental-disease-history-of-cerebrovascular-accident-without.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-neurological-or-mental-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (History of Cerebrovascular Accident without Residual Deficits)    create-chronic-neurological-or-mental-disease-history-of-cerebrovascular-accident-without.json
    condition.validate response - 201


046 Create Chronic Neurological or Mental Diseases (Mental Disorder)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-neurological-or-mental-disease-mental-diseases.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-neurological-or-mental-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Mental Disorder)    create-chronic-neurological-or-mental-disease-mental-diseases.json
    condition.validate response - 201


047 Create Chronic Neurological or Mental Diseases (Migraine)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-neurological-or-mental-disease-migraine.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-neurological-or-mental-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Migraine)    create-chronic-neurological-or-mental-disease-migraine.json
    condition.validate response - 201


048 Create Chronic Neurological or Mental Diseases (Multiple Sclerosis)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-neurological-or-mental-disease-multiple-sclerosis.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-neurological-or-mental-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Multiple Sclerosis)    create-chronic-neurological-or-mental-disease-multiple-sclerosis.json
    condition.validate response - 201
    [Teardown]  TRACE GITHUB ISSUE    288  

049 Create Chronic Neurological or Mental Diseases (Parkinson's Disease)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-neurological-or-mental-disease-parkinsons.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-neurological-or-mental-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Parkinson's Disease)    create-chronic-neurological-or-mental-disease-parkinsons.json
    condition.validate response - 201
    [Teardown]  TRACE GITHUB ISSUE    288  

050 Create Chronic Neurological or Mental Diseases (Psychotic Disorder)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-chronic-neurological-or-mental-disease-psychotic-disorder.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              chronic-neurological-or-mental-disease    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Psychotic Disorder)    create-chronic-neurological-or-mental-disease-psychotic-disorder.json
    condition.validate response - 201


051 Create Cardiovascular Diseases (Cardiac Arrhythmia)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-cardiovascular-diseases-cardiac-arrhythmia.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              cardiovascular-diseases    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (Cardiac Arrhythmia)    create-cardiovascular-diseases-cardiac-arrhythmia.json
    condition.validate response - 201


052 Create Cardiovascular Diseases (Cardiovascular Disease)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-cardiovascular-diseases-cardiovascular-disease.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              cardiovascular-diseases    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (Cardiovascular Disease)    create-cardiovascular-diseases-cardiovascular-disease.json
    condition.validate response - 201


053 Create Cardiovascular Diseases (Carotid Artery Stenosis)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-cardiovascular-diseases-carotid-artery-stenosis.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              cardiovascular-diseases    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (Carotid Artery Stenosis)    create-cardiovascular-diseases-carotid-artery-stenosis.json
    condition.validate response - 201
    [Teardown]  TRACE GITHUB ISSUE    288  

054 Create Cardiovascular Diseases (Coronary Arteriosclerosis)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-cardiovascular-diseases-coronary-arteriosclerosis.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              cardiovascular-diseases    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (Coronary Arteriosclerosis)    create-cardiovascular-diseases-coronary-arteriosclerosis.json
    condition.validate response - 201
    [Teardown]  TRACE GITHUB ISSUE    288  

055 Create Cardiovascular Diseases (Heart Failure)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-cardiovascular-diseases-heart-failure.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              cardiovascular-diseases    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (Heart Failure)    create-cardiovascular-diseases-heart-failure.json
    condition.validate response - 201


056 Create Cardiovascular Diseases (History of Heart Revascularization)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-cardiovascular-diseases-history-of-heart-revascularization.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              cardiovascular-diseases    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (History of Heart Revascularization)    create-cardiovascular-diseases-history-of-heart-revascularization.json
    condition.validate response - 201


057 Create Cardiovascular Diseases (History of Myocardial Infarction)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-cardiovascular-diseases-history-of-myocardial-infarction.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              cardiovascular-diseases    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (History of Myocardial Infarction)    create-cardiovascular-diseases-history-of-myocardial-infarction.json
    condition.validate response - 201


058 Create Cardiovascular Diseases (Hypertensive Disorder)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-cardiovascular-diseases-hypertensive-disorder.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              cardiovascular-diseases    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (Hypertensive Disorder)    create-cardiovascular-diseases-hypertensive-disorder.json
    condition.validate response - 201


059 Create Cardiovascular Diseases (Peripheral Arterial Occlusive Disease)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-cardiovascular-diseases-peripheral-arterial-occlusive-disease.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              cardiovascular-diseases    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (Peripheral Arterial Occlusive Disease)    create-cardiovascular-diseases-peripheral-arterial-occlusive-disease.json
    condition.validate response - 201


060 Create Human Immunodeficiency Virus Infection (Present)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-human-immunodeficiency-virus-infection-present.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              human-inmunodeficiency-virus-infection    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create human immunodeciency virus infection    Human Immunodeficiency Virus Infection (Present)    create-human-immunodeficiency-virus-infection-present.json
    condition.validate response - 201


061 Create Human Immunodeficiency Virus Infection (Absent)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-human-immunodeficiency-virus-infection-absent.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              human-inmunodeficiency-virus-infection    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create human immunodeciency virus infection    Human Immunodeficiency Virus Infection (Absent)    create-human-immunodeficiency-virus-infection-absent.json
    condition.validate response - 201


062 Create Human Immunodeficiency Virus Infection (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-human-immunodeficiency-virus-infection-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              human-inmunodeficiency-virus-infection    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create human immunodeciency virus infection    Human Immunodeficiency Virus Infection (Unknown)    create-human-immunodeficiency-virus-infection-unknown.json
    condition.validate response - 201


063 Create Gastrointestinal Ulcer (Present)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-gastrointestinal-ulcer-present.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              gastrointestinal-ulcer    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create gastrointestinal ulcer    Gastrointestinal Ulcer (Present)    create-gastrointestinal-ulcer-present.json
    condition.validate response - 201


064 Create Gastrointestinal Ulcer (Absent)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-gastrointestinal-ulcer-absent.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              gastrointestinal-ulcer    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create gastrointestinal ulcer    Gastrointestinal Ulcer (Absent)    create-gastrointestinal-ulcer-absent.json
    condition.validate response - 201


065 Create Gastrointestinal Ulcer (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-gastrointestinal-ulcer-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              gastrointestinal-ulcer    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create gastrointestinal ulcer    Gastrointestinal Ulcer (Unknown)    create-gastrointestinal-ulcer-unknown.json
    condition.validate response - 201


066 Create History of Organ Recipient (Present)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-history-of-organ-recipient-present.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              history-of-organ-recipient    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create history of organ recipient    History of Organ Recipient (Present)    create-history-of-organ-recipient-present.json
    condition.validate response - 201


067 Create History of Organ Recipient (Absent)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-history-of-organ-recipient-absent.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              history-of-organ-recipient    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create history of organ recipient    History of Organ Recipient (Absent)    create-history-of-organ-recipient-absent.json
    condition.validate response - 201


068 Create History of Organ Recipient (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-history-of-organ-recipient-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              history-of-organ-recipient    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create history of organ recipient    History of Organ Recipient (Unknown)    create-history-of-organ-recipient-unknown.json
    condition.validate response - 201


069 Create Complications of Covid-19 - Cerebrovascular Accident (Disorder)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-complications-of-covid-19-cerebrovascular-accident.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              complications-of-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create complications of covid-19    Complications of Covid-19 - Cerebrovascular Accident (Disorder)    create-complications-of-covid-19-cerebrovascular-accident.json
    condition.validate response - 201


070 Create Complications of Covid-19 - Infectious Agent in Bloodstream (Finding - Present)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-complications-of-covid-19-infectious-agent-in-bloodstream-present.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              complications-of-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create complications of covid-19    Complications of Covid-19 - Infectious Agent in Bloodstream (Finding - Present)    create-complications-of-covid-19-infectious-agent-in-bloodstream-present.json
    condition.validate response - 201


071 Create Complications of Covid-19 - Infectious Agent in Bloodstream (Finding - Absent)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-complications-of-covid-19-infectious-agent-in-bloodstream-absent.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              complications-of-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create complications of covid-19    Complications of Covid-19 - Infectious Agent in Bloodstream (Finding - Absent)    create-complications-of-covid-19-infectious-agent-in-bloodstream-absent.json
    condition.validate response - 201


072 Create Complications of Covid-19 - Infectious Disease of Lung
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-complications-of-covid-19-infectious-disease-of-lung.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              complications-of-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create complications of covid-19    Complications of Covid-19 - Infectious Disease of Lung    create-complications-of-covid-19-infectious-disease-of-lung.json
    condition.validate response - 201


073 Create Complications of Covid-19 - Myocardial Infarction (Disorder - Present)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-complications-of-covid-19-myocardial-infarction-present.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              complications-of-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create complications of covid-19    Complications of Covid-19 - Myocardial Infarction (Disorder - Present)    create-complications-of-covid-19-myocardial-infarction-present.json
    condition.validate response - 201


074 Create Complications of Covid-19 - Myocardial Infarction (Disorder - Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-complications-of-covid-19-myocardial-infarction-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              complications-of-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create complications of covid-19    Complications of Covid-19 - Myocardial Infarction (Disorder - Unknown)    create-complications-of-covid-19-myocardial-infarction-unknown.json
    condition.validate response - 201


075 Create Complications of Covid-19 - Pre-Renal acute Kidney Injury
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-complications-of-covid-19-pre-renal-acute-kidney-injury.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              complications-of-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create complications of covid-19    Complications of Covid-19 - Pre-Renal acute Kidney Injury    create-complications-of-covid-19-pre-renal-acute-kidney-injury.json
    condition.validate response - 201


076 Create Complications of Covid-19 - Pulmonary Embolism
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-complications-of-covid-19-pulmonary-embolism.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              complications-of-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create complications of covid-19    Complications of Covid-19 - Pulmonary Embolism    create-complications-of-covid-19-pulmonary-embolism.json
    condition.validate response - 201


077 Create Complications of Covid-19 - Venous Thrombosis
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-complications-of-covid-19-venous-thrombosis.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
    [Tags]              complications-of-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create complications of covid-19    Complications of Covid-19 - Venous Thrombosis    create-complications-of-covid-19-venous-thrombosis.json
    condition.validate response - 201


078 Create Condition Symptoms-Covid-19 sct
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-symptoms-covid-19-present_sct.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	symptoms-covid-19    valid    alternative    sct

    ehr.create new ehr    000_ehr_status.json
    condition.create symptoms-covid-19    Symptoms-Covid-19 sct    Symptom/create-symptoms-covid-19-present_sct.json
    condition.validate response - 201


079 Create Condition Symptoms-Covid-19 icd10
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-symptoms-covid-19-present_icd10.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	symptoms-covid-19    invalid    alternative    icd10

    ehr.create new ehr    000_ehr_status.json
    condition.create symptoms-covid-19    Symptoms-Covid-19 icd10   Symptom/create-symptoms-covid-19-present_icd10.json
    condition.validate response - 422 (with error message)      422     Condition.code.coding:sct: minimum required = 1, but only found 0


080 Create Condition Symptoms-Covid-19 alphaid
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-symptoms-covid-19-present_alphaid.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to condition endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	symptoms-covid-19    invalid    alternative    alphaid

    ehr.create new ehr    000_ehr_status.json
    condition.create symptoms-covid-19    Symptoms-Covid-19 alphaid    Symptom/create-symptoms-covid-19-present_alphaid.json
    condition.validate response - 422 (with error message)      422        Condition.code.coding:sct: minimum required = 1, but only found 0
