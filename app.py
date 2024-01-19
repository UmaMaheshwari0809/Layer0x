# Example using Flask and MongoDB (Python)
from flask import Flask, request, jsonify
from flask_pymongo import PyMongo

app = Flask(__name__)
app.config['MONGO_URI'] = 'mongodb://localhost:27017/blockchainDB'
mongo = PyMongo(app)

# Create
@app.route('/blockchains', methods=['POST'])
def add_blockchain():
    data = request.get_json()
    mongo.db.blockchains.insert_one(data)
    return jsonify({"message": "Blockchain added successfully"}), 201

# Read
@app.route('/blockchains', methods=['GET'])
def get_blockchains():
    blockchains = mongo.db.blockchains.find()
    result = []
    for blockchain in blockchains:
        result.append({
            'name': blockchain['name'],
            'status': blockchain['status'],
            'height': blockchain['height']
        })
    return jsonify({"blockchains": result})

# Update
@app.route('/blockchains/<name>', methods=['PUT'])
def update_blockchain(name):
    data = request.get_json()
    mongo.db.blockchains.update_one({'name': name}, {'$set': data})
    return jsonify({"message": "Blockchain updated successfully"})

    


@app.route('/blockchais/status', methods=['GET'])
def get_blockchains_status():
    blockchains = mongo.db.blockchains.find()
    result = []
    for blockchain in blockchains:
        # Assume you have an RPC function to get blockchain status
        status = get_blockchain_status(blockchain['rpc_endpoint'])
        result.append({
            'name': blockchain['name'],
            'status': status,
            'height': blockchain['height']
        })
    return jsonify({"blockchains": result})


if __name__ == '__main__':
    app.run(debug=True)
