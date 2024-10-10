# Databricks notebook source
import subprocess

def get_git_branch():
    result = subprocess.run(['git', 'symbolic-ref', '--short', 'HEAD'], stdout=subprocess.PIPE)
    return result.stdout.decode().strip()

branch_name = get_git_branch()
print(branch_name)

# COMMAND ----------



# COMMAND ----------

# MAGIC %sh
# MAGIC apt-get -y install git

# COMMAND ----------

# MAGIC %sh
# MAGIC git -v

# COMMAND ----------



# COMMAND ----------

import requests

# Set your Databricks API token
api_token = dbutils.notebook.entry_point.getDbutils().notebook().getContext().apiToken().get()

# Set the Databricks instance URL
databricks_instance = dbutils.notebook.entry_point.getDbutils().notebook().getContext().apiUrl().get()

# Define the API endpoint for getting repositories
api_endpoint = "/api/2.0/repos/128817990695957"

# Create headers with the API token
headers = {"Authorization": f"Bearer {api_token}"}

# Make the GET request to retrieve repositories
response = requests.get(f"{databricks_instance}{api_endpoint}", headers=headers)

# Check if the request was successful (status code 200)
if response.status_code == 200:
    repos_data = response.json()
    # Handle the repos_data as needed
    print("Repositories Data:", repos_data)
else:
    print("Failed to retrieve repositories.")

# COMMAND ----------

import requests

# Set your Databricks API token
api_token = dbutils.notebook.entry_point.getDbutils().notebook().getContext().apiToken().get()

# Set the Databricks instance URL
databricks_instance = dbutils.notebook.entry_point.getDbutils().notebook().getContext().apiUrl().get()

# Define the API endpoint for getting repositories
api_endpoint = "/api/2.0/repos/128817990695957"

# Create headers with the API token
headers = {"Authorization": f"Bearer {api_token}"}

# Make the GET request to retrieve repositories
response = requests.get(f"{databricks_instance}{api_endpoint}", headers=headers)

# Check if the request was successful (status code 200)
if response.status_code == 200:
    repos_data = response.json()
    # Handle the repos_data as needed
    print("Repositories Data:", repos_data)
else:
    print("Failed to retrieve repositories.")

# COMMAND ----------

import requests

# Set your Databricks API token
api_token = dbutils.notebook.entry_point.getDbutils().notebook().getContext().apiToken().get()

# Set the Databricks instance URL
databricks_instance = dbutils.notebook.entry_point.getDbutils().notebook().getContext().apiUrl().get()

# Define the API endpoint for getting repositories
# api_endpoint = "/api/2.0/repos/"

# Define the API endpoint for getting the repository data using repo id, we can get the repo ID from the above API
api_endpoint = "/api/2.0/repos/128817990695957"

# Create headers with the API token
headers = {"Authorization": f"Bearer {api_token}"}

# Make the GET request to retrieve repositories
response = requests.get(f"{databricks_instance}{api_endpoint}", headers=headers)

# Check if the request was successful (status code 200)
if response.status_code == 200:
    repos_data = response.json()
    # Handle the repos_data as needed
    print("Repositories Data:", repos_data)
    print("Repositories Data for branch:", repos_data["branch"])
else:
    print("Failed to retrieve repositories.")
