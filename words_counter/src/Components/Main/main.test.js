import React from 'react';
import Main from './Main';
import { shallow } from 'enzyme';

const state = {
    inputVal:     '',
    outputVal:    '',
    errorMessage: ''
};
const textInput = 'Hello World!';
const outputVal = 'Hello -1  World - 1';
const errorMessage = 'Bad request';
const mutatedStatedInput = {
    inputVal: textInput,
    outputVal: '',
    errorMessage: ''
};
const mutatedStatedOutput = {
    inputVal: '',
    outputVal: outputVal,
    errorMessage: ''
};
const mutatedStatedErrorMsg = {
    inputVal: '',
    outputVal: '',
    errorMessage: errorMessage
};
const result = shallow(<Main/>);

describe('Main component:', () => {
    beforeEach(() => {
        fetch.resetMocks();
    });

    test('Should have one \'div\' element', () => {
            expect(result.find('div').length).toBe(1);
    });

    test('Should have one \'form\' element', () => {
        expect(result.find('form').length).toBe(1);
    });

    test('Should have two \'textarea\' element', () => {
        expect(result.find('textarea').length).toBe(2);
    });

    test('Should have one \'button\' element', () => {
        expect(result.find('button').length).toBe(1);
    });

    test('Should have one \'p\' element', () => {
        expect(result.find('p').length).toBe(1);
    });

    test('textarea input value should be empty initially', () => {
        expect(result.find('textarea').first().text()).toBe('');
    });

    test('textarea output value should be empty initially', () => {
        expect(result.find('textarea').first(2).text()).toBe('');
    });

    test('component state and textarea input value should reflect according changes if ' +
        'any text input provided', () => {
        result.find('textarea').first().simulate('change', {
            target: {
                value: textInput
            }
        });
        expect(result.state()).toEqual(mutatedStatedInput);
    });

    test('Should respond to state initial change properly for input textarea', () => {
        result.setState({
            inputVal: textInput
        });
        expect(result.state()).toEqual(mutatedStatedInput);
        result.setState({
            inputVal: ''
        });
        expect(result.state()).toEqual(state);
    });

    test('Should respond to state initial change properly for output textarea', () => {
        result.setState({
            outputVal: outputVal
        });
        expect(result.state()).toEqual(mutatedStatedOutput);
        result.setState({
            outputVal: ''
        });
        expect(result.state()).toEqual(state);
    });

    test('Should respond to state initial change properly for error message', () => {
        result.setState({
            errorMessage: errorMessage
        });
        expect(result.state()).toEqual(mutatedStatedErrorMsg);
        result.setState({
            errorMessage: ''
        });
        expect(result.state()).toEqual(state);
    });

    it('Check api call when button \'PROCEED\' is pushed', () => {
        fetch.mockResponseOnce(JSON.stringify({ body: [outputVal] }),{ status: 200 });
        result.find('button').simulate('click', { preventDefault() {} });
        expect(fetch.mock.calls.length).toEqual(1);
        expect(fetch.mock.calls[0][0]).toEqual('http://localhost:8080/count');
    });
});
