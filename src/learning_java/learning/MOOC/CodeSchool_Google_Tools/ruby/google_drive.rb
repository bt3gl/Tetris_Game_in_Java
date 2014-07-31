# Example from codeschool - discover driver
# $ source "https://rubygems.org"
# $ gem 'google-api-client'

require 'google/api_client'

client = Google::APIClient.new
client.authorization.client_id = ENV['client_id']
client.authorization.client_secret = ENV['client_secret']
client.authorization.redirect_uri = ENV['redirect_uri']
client.authorization.scope = 'https://www.googleapis.com/auth/drive.file'

redirect_to client.authorization.authorization_uri


authorization_code = params[:code]

client.code = authorization_code
client.fetch_access_token!


drive =client.discovered_api('drive', 'v2')