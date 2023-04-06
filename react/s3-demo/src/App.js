import React, { useState } from 'react';
import AWS from 'aws-sdk'
import { v4 as uuidv4 } from 'uuid';

const S3_BUCKET = 'developers-attach-test';
const REGION = 'ap-northeast-2';

AWS.config.update({
  accessKeyId: process.env.REACT_APP_AWS_ACCESS_KEY_ID,
  secretAccessKey: process.env.REACT_APP_AWS_ACCESS_SECRET_KEY,
});

const myBucket = new AWS.S3({
  params: { Bucket: S3_BUCKET },
  region: REGION,
});

function App() {

  const [progress, setProgress] = useState(0);
  const [selectedFile, setSelectedFile] = useState(null);

  // 파일 선택시 호출되는 함수
  const handleFileInput = (e) => {
    setSelectedFile(e.target.files[0]);
  }

  const uploadFile = (file) => {
    
    // 파일 이름을 UUID로 생성합니다.
    const fileName = `${uuidv4()}-${file.name}`;
    console.log(fileName);

    const params = {
      ACL: 'public-read',
      Body: file,
      Bucket: S3_BUCKET,
      Key: fileName
    };

    // 파일을 업로드하고 URL을 응답받아오기 위해서는 putOvject가 아닌 upload를 사용하자.
    // myBucket.putObject(params)
    //   .on('httpUploadProgress', (evt) => {
    //     setProgress(Math.round((evt.loaded / evt.total) * 100))
    //   })
    //   .send((err) => {
    //     if (err) console.log(err)
    //   })
    
    myBucket.upload(params)
      .on('httpUploadProgress', (evt) => {
        setProgress(Math.round((evt.loaded / evt.total) * 100))
      })
      .send((err, data) => {
        if (err) {
          console.log(err);
          return;
        }
        // S3에 저장된 파일 URL을 응답으로 받아옵니다.
        console.log(`File uploaded successfully. ${data.Location}`);
      });
  }

  return <div>
    <div>Native SDK File Upload Progress is {progress}%</div>
    <input type="file" onChange={handleFileInput} />
    <button onClick={() => uploadFile(selectedFile)}> Upload to S3</button>
  </div>
}

export default App;