INVALID TEST
'ALTER TABLE authors ADD COLUMN varcharColumn STRING
ALTER TABLE authors ADD COLUMN intColumn INT64
ALTER TABLE authors ADD COLUMN dateColumn date
UPDATE authors SET varcharColumn = "INITIAL_VALUE"" WHERE 1=1
UPDATE authors SET intColumn = 5 WHERE 1=1
UPDATE authors SET dateColumn = "2020-09-21" WHERE 1=1
'