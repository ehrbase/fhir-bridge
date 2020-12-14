# Copyright (c) 2020 P. Wohlfarth (Appsfactory GmbH), Wladislaw Wagner (Vitasystems GmbH) &
# Dave Petzold (Appsfactory GmbH)
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
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]              diagnose-condition    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create diagnose condition    create-condition-default.json
    condition.validate response - 201


002 Create Condition Using Invalid Profile
    [Documentation]     1. create EHR
    ...                 2. POST to Condition endpoint using invalid profile
    [Tags]              invalid

    ehr.create new ehr    000_ehr_status.json
    condition.create diagnose condition    create-condition-with-invalid-profile.json
    condition.validate response - 422 (Unprocessable Entity)


003 Create Condition Symptoms-Covid-19 (Present)
    [Documentation]     1. create EHR
    ...                 2. create condition symptoms-covid-19 (status: present)
    [Tags]              symptoms-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create symptoms-covid-19    create-symptoms-covid-19-present.json
    condition.validate response - 201


004 Create Condition Symptoms-Covid-19 (Absent)
    [Documentation]     1. create EHR
    ...                 2. create condition symptoms-covid-19 (status: absent)
    [Tags]              symptoms-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create symptoms-covid-19    create-symptoms-covid-19-absent.json
    condition.validate response - 201


005 Create Condition Symptoms-Covid-19 (Unknown)
    [Documentation]     1. create EHR
    ...                 2. create condition symptoms-covid-19 (status: unknown)
    [Tags]              symptoms-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create symptoms-covid-19    create-symptoms-covid-19-unknown.json
    condition.validate response - 201


006 Create Condition Diabetes Mellitus
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             diabetes-mellitus    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create diabetes mellitus    Diabetes Mellitus    create-diabetes-mellitus.json
    condition.validate response - 201


007 Create Condition Diabetes Mellitus Type 1
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             diabetes-mellitus    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create diabetes mellitus    Diabetes Mellitus Type 1    create-diabetes-mellitus-type-1.json
    condition.validate response - 201


008 Create Condition Diabetes Mellitus Type 2
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             diabetes-mellitus    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create diabetes mellitus    Diabetes Mellitus Type 2    create-diabetes-mellitus-type-2.json
    condition.validate response - 201


009 Create Condition Diabetes Mellitus Type 2 Insulin Treated
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             diabetes-mellitus    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create diabetes mellitus    Diabetes Mellitus Type 2 Insulin Treated    create-diabetes-mellitus-type-2-insulin-treated.json
    condition.validate response - 201


010 Create Rheumatological Immunological Diseases (Rheumatism)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             diabetes-mellitus    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create rheumatological immunological diseases    Rheumatological Immunological Diseases (Rheumatism)    create-rheumatological-immunological-diseases-rheumatism.json
    condition.validate response - 201


011 Create Rheumatological Immunological Diseases (Rheumatoid Arthritis)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             diabetes-mellitus    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create rheumatological immunological diseases    Rheumatological Immunological Diseases (Rheumatoid Arthritis)    create-rheumatological-immunological-diseases-rheumatoid-arthritis.json
    condition.validate response - 201


012 Create Chronic Lung Diseases
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic lung disease    create-chronic-lung-disease.json
    condition.validate response - 201


013 Create Chronic Obstructive Lung Disease
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic obstructive lung disease    create-chronic-obstructive-lung-disease.json
    condition.validate response - 201


014 Create Chronic Lung Disease (Fibrosis of Lung)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Fibrosis of Lung)    create-chronic-lung-disease-fibrosis-of-lung.json
    condition.validate response - 201


015 Create Chronic Lung Disease (Asthma)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Asthma)    create-chronic-lung-disease-asthma.json
    condition.validate response - 201


016 Create Chronic Lung Disease (Cystic Fibrosis)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Cystic Fibrosis)    create-chronic-lung-disease-cystic-fibrosis.json
    condition.validate response - 201


017 Create Chronic Lung Disease (Extreme Obesity with Alveolar Hypoventilation)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Extreme Obesity with Alveolar Hypoventilation)    create-chronic-lung-disease-with-alveolar-hypoventilation.json
    condition.validate response - 201


018 Create Chronic Lung Disease (Pulmonary hypertension)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Pulmonary hypertension)    create-chronic-lung-disease-pulmonary-hypertension.json
    condition.validate response - 201


019 Create Chronic Lung Disease (Sleep Apnea)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Sleep Apnea)    create-chronic-lung-disease-sleep-apnea.json
    condition.validate response - 201


020 Create Chronic Lung Disease (Obstructive Sleep Apnea Syndrome)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Obstructive Sleep Apnea Syndrome)    create-chronic-lung-disease-obstructive-sleep-apnea.json
    condition.validate response - 201


021 Create Chronic Liver Disease
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-liver-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic liver diseases    Chronic Liver Disease    create-chronic-liver-disease.json
    condition.validate response - 201


022 Create Chronic Liver Disease (Autoimmune Liver Disease)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-liver-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic liver diseases    Chronic Liver Disease (Autoimmune Liver Disease)    create-chronic-liver-disease-autoimmune.json
    condition.validate response - 201


023 Create Chronic Liver Disease (Chronic Viral Hepatitis)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-liver-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic liver diseases    Chronic Liver Disease (Chronic Viral Hepatitis)    create-chronic-liver-disease-chronic-viral-hepatitis.json
    condition.validate response - 201


024 Create Chronic Liver Disease (Cirrhosis of Liver)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-liver-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic liver diseases    Chronic Liver Disease (Cirrhosis of Liver)    create-chronic-liver-disease-cirrhosis-of-liver.json
    condition.validate response - 201


025 Create Chronic Liver Disease (Steatosis of Liver)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-liver-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic liver diseases    Chronic Liver Disease (Steatosis of Liver)    create-chronic-liver-disease-steatosis-of-liver.json
    condition.validate response - 201


026 Create Malignant Neoplastic Disease (Absent)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             malignant-neoplastic-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create malignant neoplastic disease    create-malignant-neoplastic-disease-absent.json
    condition.validate response - 201


027 Create Malignant Neoplastic Disease (Present Active)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             malignant-neoplastic-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create malignant neoplastic disease    create-malignant-neoplastic-disease-present-active.json
    condition.validate response - 201


028 Create Malignant Neoplastic Disease (Present Remission)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             malignant-neoplastic-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create malignant neoplastic disease    create-malignant-neoplastic-disease-present-remission.json
    condition.validate response - 201


029 Create Malignant Neoplastic Disease (Unknown)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             malignant-neoplastic-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create malignant neoplastic disease    create-malignant-neoplastic-disease-unknown.json
    condition.validate response - 201


030 Create Diagnosis Covid-19 (Absent)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             diagnosis-covid-19    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create diagnosis covid-19    create-diagnosis-covid-19-absent.json
    condition.validate response - 201


031 Create Diagnosis Covid-19 (Present Active)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             diagnosis-covid-19    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create diagnosis covid-19    create-diagnosis-covid-19-present-active.json
    condition.validate response - 201


032 Create Diagnosis Covid-19 (Present Remission)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             diagnosis-covid-19    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create diagnosis covid-19    create-diagnosis-covid-19-present-remission.json
    condition.validate response - 201

# TODO: CLARIFY IF UNKNOW STATUS FOR DIAGNOSIS MAKES SENSE
032 Create Diagnosis Covid-19 (Unknown)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             diagnosis-covid-19    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create diagnosis covid-19    create-diagnosis-covid-19-unknown.json
    condition.validate response - 201


033 Create Chronic Kidney Disease (Disorder - Present)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-kidney-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic kidney diseases    Chronic Kidney Disease (Disorder - Present)    create-chronic-kidney-disease-present.json
    condition.validate response - 201


034 Create Chronic Kidney Disease (Disorder - Absent)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-kidney-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic kidney diseases    Chronic Kidney Disease (Disorder - Absent)    create-chronic-kidney-disease-absent.json
    condition.validate response - 201


035 Create Chronic Kidney Disease (Disorder - Unknown)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-kidney-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic kidney diseases    Chronic Kidney Disease (Disorder - Unknown)    create-chronic-kidney-disease-unknown.json
    condition.validate response - 201


036 Create Chronic Kidney Disease (Disorder - stage 5 on dialysis)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-kidney-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic kidney diseases    Chronic Kidney Disease (Disorder - stage 5 on dialysis)    create-chronic-kidney-disease-stage-5-dialysis.json
    condition.validate response - 201


037 Create Chronic Neurological or Mental Diseases (Anxiety Disorder)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-neurological-or-mental-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Anxiety Disorder)    create-chronic-neurological-or-mental-disease-anxiety.json
    condition.validate response - 201


038 Create Chronic Neurological or Mental Diseases (Chronic Nervous System Disorder)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-neurological-or-mental-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Chronic Nervous System Disorder)    create-chronic-neurological-or-mental-disease-chronic-nervous-system.json
    condition.validate response - 201


039 Create Chronic Neurological or Mental Diseases (Combined disorder of muscle AND peripheral nerve)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-neurological-or-mental-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Combined disorder of muscle AND peripheral nerve)    create-chronic-neurological-or-mental-disease-muscle-and-peripheral-nerve.json
    condition.validate response - 201


040 Create Chronic Neurological or Mental Diseases (Dementia)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-neurological-or-mental-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Dementia)    create-chronic-neurological-or-mental-disease-dementia.json
    condition.validate response - 201


041 Create Chronic Neurological or Mental Diseases (Depressive Disorder)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-neurological-or-mental-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Depressive Disorder)    create-chronic-neurological-or-mental-disease-depressive.json
    condition.validate response - 201


042 Create Chronic Neurological or Mental Diseases (Epilepsy)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-neurological-or-mental-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Epilepsy)    create-chronic-neurological-or-mental-disease-epilepsy.json
    condition.validate response - 201


043 Create Chronic Neurological or Mental Diseases (History of Cerebrovascular Accident with Residual Deficits)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-neurological-or-mental-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (History of Cerebrovascular Accident with Residual Deficits)    create-chronic-neurological-or-mental-disease-history-of-cerebrovascular-accident-with.json
    condition.validate response - 201


044 Create Chronic Neurological or Mental Diseases (History of Cerebrovascular Accident without Residual Deficits)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-neurological-or-mental-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (History of Cerebrovascular Accident without Residual Deficits)    create-chronic-neurological-or-mental-disease-history-of-cerebrovascular-accident-without.json
    condition.validate response - 201


045 Create Chronic Neurological or Mental Diseases (Mental Disorder)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-neurological-or-mental-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Mental Disorder)    create-chronic-neurological-or-mental-disease-mental-diseases.json
    condition.validate response - 201


046 Create Chronic Neurological or Mental Diseases (Migraine)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-neurological-or-mental-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Migraine)    create-chronic-neurological-or-mental-disease-migraine.json
    condition.validate response - 201


047 Create Chronic Neurological or Mental Diseases (Multiple Sclerosis)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-neurological-or-mental-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Multiple Sclerosis)    create-chronic-neurological-or-mental-disease-multiple-sclerosis.json
    condition.validate response - 201


048 Create Chronic Neurological or Mental Diseases (Parkinson's Disease)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-neurological-or-mental-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Parkinson's Disease)    create-chronic-neurological-or-mental-disease-parkinsons.json
    condition.validate response - 201


048 Create Chronic Neurological or Mental Diseases (Psychotic Disorder)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             chronic-neurological-or-mental-disease    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic neurological or mental diseases    Chronic Neurological or Mental Diseases (Psychotic Disorder)    create-chronic-neurological-or-mental-disease-psychotic-disorder.json
    condition.validate response - 201


049 Create Cardiovascular Diseases (Cardiac Arrhythmia)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             cardiovascular-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (Cardiac Arrhythmia)    create-cardiovascular-diseases-cardiac-arrhythmia.json
    condition.validate response - 201


050 Create Cardiovascular Diseases (Cardiovascular Disease)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             cardiovascular-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (Cardiovascular Disease)    create-cardiovascular-diseases-cardiovascular-disease.json
    condition.validate response - 201


051 Create Cardiovascular Diseases (Carotid Artery Stenosis)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             cardiovascular-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (Carotid Artery Stenosis)    create-cardiovascular-diseases-carotid-artery-stenosis.json
    condition.validate response - 201


052 Create Cardiovascular Diseases (Coronary Arteriosclerosis)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             cardiovascular-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (Coronary Arteriosclerosis)    create-cardiovascular-diseases-coronary-arteriosclerosis.json
    condition.validate response - 201


053 Create Cardiovascular Diseases (Heart Failure)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             cardiovascular-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (Heart Failure)    create-cardiovascular-diseases-heart-failure.json
    condition.validate response - 201


054 Create Cardiovascular Diseases (History of Heart Revascularization)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             cardiovascular-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (History of Heart Revascularization)    create-cardiovascular-diseases-history-of-heart-revascularization.json
    condition.validate response - 201


055 Create Cardiovascular Diseases (History of Myocardial Infarction)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             cardiovascular-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (History of Myocardial Infarction)    create-cardiovascular-diseases-history-of-myocardial-infarction.json
    condition.validate response - 201


056 Create Cardiovascular Diseases (Hypertensive Disorder)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             cardiovascular-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (Hypertensive Disorder)    create-cardiovascular-diseases-hypertensive-disorder.json
    condition.validate response - 201


057 Create Cardiovascular Diseases (Peripheral Arterial Occlusive Disease)
	[Documentation]    1. create new EHR Patient record
	...                2. update {{patient_id}} in example json
    ...                3. POST example json to Condition endpoint
	...                4. validate the response status
    [Tags]             cardiovascular-diseases    valid    not-ready
    ehr.create new ehr    000_ehr_status.json
    condition.create cardiovascular diseases    Cardiovascular Diseases (Peripheral Arterial Occlusive Disease)    create-cardiovascular-diseases-peripheral-arterial-occlusive-disease.json
    condition.validate response - 201
