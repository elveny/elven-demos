import dva from 'dva';
import './index.html';
import './index.css';

// 1. Initialize
// const app = dva();
const app = dva({
  initialState:{
    products:[
      { name: 'dva', id: 0 },
      { name: 'antd1', id: 1 },
      { name: 'antd2', id: 2 },
      { name: 'antd3', id: 3 },
      { name: 'antd4', id: 4 },
    ]
  }
});
// 2. Plugins
// app.use({});

// 3. Model
// app.model(require('./models/example'));
app.model(require('./models/products'));

// 4. Router
app.router(require('./router'));

// 5. Start
app.start('#root');
