output "dataset_id" {
  value       = module.bigquery.dataset_id
  description = "Unique id for the dataset being provisioned"
}

output "dataset_name" {
  value       = module.bigquery.dataset_name
  description = "Friendly name for the dataset being provisioned"
}
